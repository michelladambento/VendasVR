package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.converters.PurchaserOrderConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.DateOrderValidException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultAlreadyExistException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultNotFoundException;
import com.michell.vendas.vr.client.VendasVR.exceptions.TotalOrderValidException;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PurchaserOrderService {

    @Autowired
    private PurchaserOrderConverter purchaserOrderConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerRepository customerRepository;

    public void savePurchaserOrder(PurchaseOrderDTO dto){

        //find Customer entity e converter
        CustomerDTO customerDTO = dto.getCustomer();
        CustomerEntity customerEntity = findCustomer(customerDTO);
        checkDateIsValid(customerEntity, dto);
        checkTotalOrder(customerEntity, dto);
        hasDuplicateProductInList(dto.getProductItens());
        PurchaserOrderEntity purchaserConverted = purchaserOrderConverter.converter(dto);
//        falta terminar

    }

    private void checkDateIsValid(CustomerEntity customerEntity, PurchaseOrderDTO dto){
        LocalDate closingDateAt = customerEntity.getClosingDateAt();
        LocalDate orderDateAt = dto.getOrderDateAt();
        if (orderDateAt.isAfter(closingDateAt))
            throw new DateOrderValidException(closingDateAt);
    }

    private void checkTotalOrder(CustomerEntity customerEntity, PurchaseOrderDTO purchaseOrderDTO ){
        List<ProductItemDTO> productItens = purchaseOrderDTO.getProductItens();
        Double totalOrder = 0.0;
        for (ProductItemDTO productItem : productItens){
            Integer qtd = productItem.getQtd();
            Double unitPrice = productItem.getUnitPrice();
            totalOrder = totalOrder + (qtd * unitPrice);
        }
        Double purchaseLimit = customerEntity.getPurchaseLimit();
        LocalDate closingDateAt = customerEntity.getClosingDateAt();
        if(totalOrder > purchaseLimit)
            throw new TotalOrderValidException(purchaseLimit, closingDateAt);
    }

    private void hasDuplicateProductInList(List<ProductItemDTO> productItems){
        Set<ProductDTO> seenProducts = new HashSet<>();
        for (ProductItemDTO item : productItems) {
            ProductDTO product = item.getProduct();
            if (seenProducts.contains(product)) {
                String message = String.format("Produto: (%s) já existe na lista.", product.getDescription());
                throw new DefaultAlreadyExistException(message);
            }
            seenProducts.add(product);
        }
    }

    private CustomerEntity findCustomer(CustomerDTO customerDTO){
        Long customerId = customerDTO.getId();
        Optional<CustomerEntity> optCustomerEntity = customerRepository.findById(customerId);
        if(!optCustomerEntity.isPresent()){
            String message = String.format("Cliente ID:(%s) não encontrado.", customerId);
            throw new DefaultNotFoundException(message);
        }
        return optCustomerEntity.get();
    }

}
