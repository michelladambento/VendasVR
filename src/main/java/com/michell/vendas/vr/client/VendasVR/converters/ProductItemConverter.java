package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductItemConverter {


    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private PurchaserOrderConverter purchaserOrderConverter;


    public ProductItemEntity converter(ProductItemDTO dto){
        ProductItemEntity entity = new ProductItemEntity();
        ProductDTO productDTO = dto.getProduct();
        entity.setId(dto.getId());
        entity.setProduct(productConverter.converter(productDTO));
        entity.setPurchaserOrder(null);
        entity.setUnitPrice(dto.getUnitPrice());
        return entity;
    }

    public List<ProductItemEntity> converter(List<ProductItemDTO> productItensDTOS){
        List<ProductItemEntity> productItemEntity = new ArrayList<>();
        for (ProductItemDTO productItemDTO : productItensDTOS){
            productItemEntity.add(converter(productItemDTO));
        }
        return productItemEntity;
    }


}
