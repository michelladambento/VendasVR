package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaserOrderConverterImpl implements PurchaserOrderConverter {

    @Autowired
    private CustomerConverter customerConverterImpl;

    @Autowired
    private ProductItemConverter productItemConverterImpl;


    @Override
    public PurchaserOrderEntity converter(PurchaseOrderDTO dto){

//        PurchaserOrderEntity entity = new PurchaserOrderEntity();
//        CustomerDTO customerDTO = dto.getCustomer();
//        List<ProductItemDTO> productItensDTO = dto.getProductItens();
//        entity.setCustomer(customerConverterImpl.converter(customerDTO));
//        entity.setOrderDateAt(dto.getOrderDateAt());
//        entity.setOrderTotal(dto.getOrderTotal());
//        List<ProductItemEntity> productItens = new ArrayList<>();
//        setProductItens(dto, entity, productItens);
//        entity.setProductItens(productItens);
//        return entity;
        return null;
    }

    private void setProductItens(PurchaseOrderDTO dto, PurchaserOrderEntity entity, List<ProductItemEntity> productItens) {
        for (ProductItemDTO itemDTO : dto.getProductItens()) {
            ProductItemEntity itemEntity = productItemConverterImpl.converter(itemDTO);
            itemEntity.setPurchaserOrder(entity);
            productItens.add(itemEntity);
        }
    }

}
