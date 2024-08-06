package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductItemConverterImpl implements ProductItemConverter {


    @Autowired
    @Lazy
    private ProductConverter productConverterImpl;

    @Autowired
    @Lazy
    private PurchaserOrderConverter purchaserOrderConverterImpl;


    @Override
    public ProductItemEntity converter(ProductItemDTO dto){
        ProductItemEntity entity = new ProductItemEntity();
        ProductDTO productDTO = dto.getProduct();
        entity.setId(dto.getId());
        entity.setProduct(productConverterImpl.converter(productDTO));
        entity.setPurchaserOrder(null);
        entity.setUnitPrice(dto.getUnitPrice());
        return entity;
    }

    @Override
    public List<ProductItemEntity> converter(List<ProductItemDTO> productItensDTOS){
        List<ProductItemEntity> productItemEntity = new ArrayList<>();
        for (ProductItemDTO productItemDTO : productItensDTOS){
            productItemEntity.add(converter(productItemDTO));
        }
        return productItemEntity;
    }


}
