package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverterImpl;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.DateOrderValidException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultNotFoundException;
import com.michell.vendas.vr.client.VendasVR.exceptions.TotalOrderValidException;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductItemRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.PurchaserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PurchaserOrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private PurchaserOrderRepository purchaserOrderRepository;

    public void savePurchaserOrder(PurchaseOrderDTO dto){

        Optional<CustomerEntity> optCustomerEntity = customerRepository.findById(dto.getCustomerId());
        if(!optCustomerEntity.isPresent()){
            String message = String.format("Cliente de ID (%s) não encontrado",dto.getCustomerId());
            throw new DefaultNotFoundException(message);
        }
        CustomerEntity customerEntityFounded = optCustomerEntity.get();
        checkDateIsValid(customerEntityFounded, dto);
        checkTotalOrder(customerEntityFounded, dto);

        List<ProductItemDTO> productItens = dto.getProductItens();
        if(productItens.isEmpty())
            throw new DefaultNotFoundException("A lista de produto não pode ser vazio.");

        checkForDuplicateIdsInList(productItens);

        // trecho mais complexo
        List<ProductItemEntity> productsEntities = new ArrayList<>();
        for(ProductItemDTO productItem: productItens){
            Long productIdDto = productItem.getProductId();
            Optional<ProductEntity> optProductEntity = productRepository.findById(productIdDto);
            if(!optProductEntity.isPresent())
                throw new DefaultNotFoundException(String.format("Produto de ID(%s) não encontrado.",productIdDto));
            ProductEntity productEntity = optProductEntity.get();

            ProductItemEntity productItemEntity = new ProductItemEntity();
            productItemEntity.setProduct(productEntity);
            productItemEntity.setQtd(productItem.getQtd());
            productItemEntity.setUnitPrice(productItem.getUnitPrice());
            productsEntities.add(productItemEntity);
        }

        // Cria e configura a entidade do pedido
        PurchaserOrderEntity purchaserOrderEntity = new PurchaserOrderEntity();
        CustomerEntity customerEntityWithPurchaseLimitUpdated = updatePurchaseLimit(customerEntityFounded, dto.getOrderTotal());
        purchaserOrderEntity.setCustomer(customerEntityWithPurchaseLimitUpdated);
        purchaserOrderEntity.setOrderDateAt(dto.getOrderDateAt());
        purchaserOrderEntity.setOrderTotal(dto.getOrderTotal());

        // Associa os itens de produto ao pedido
        for (ProductItemEntity itemEntity : productsEntities) {
            itemEntity.setPurchaserOrder(purchaserOrderEntity);
        }
        purchaserOrderEntity.setProductItens(productsEntities);

        purchaserOrderRepository.saveAndFlush(purchaserOrderEntity);
    }

    public void checkForDuplicateIdsInList(List<ProductItemDTO> items) {
        Set<Long> seenIds = new HashSet<>();
        for (ProductItemDTO item : items) {
            Long id = item.getProductId();
            if (id != null) {
                if (seenIds.contains(id)) {
                    throw new DefaultNotFoundException(String.format("Produto de ID:(%s) duplicado.", id));
                }
                seenIds.add(id);
            }
        }
    }

    private CustomerEntity updatePurchaseLimit(CustomerEntity customerEntity, Double orderTotal){
        Double purchaseLimit = customerEntity.getPurchaseLimit();
        Double total = purchaseLimit - orderTotal;
        customerEntity.setPurchaseLimit(total);
        return customerEntity;
    }

    private void checkDateIsValid(CustomerEntity customerEntity, PurchaseOrderDTO dto){
        LocalDate closingDateAt = customerEntity.getClosingDateAt();
        LocalDate orderDateAt = dto.getOrderDateAt();
        if (orderDateAt.isAfter(closingDateAt))
            throw new DateOrderValidException(closingDateAt);
    }

    private void checkTotalOrder(CustomerEntity customerEntity, PurchaseOrderDTO purchaseOrderDTO ){
        Double orderTotal = purchaseOrderDTO.getOrderTotal();
        Double purchaseLimit = customerEntity.getPurchaseLimit();
        LocalDate closingDateAt = customerEntity.getClosingDateAt();
        if(orderTotal > purchaseLimit)
            throw new TotalOrderValidException(purchaseLimit, closingDateAt);
    }

}
