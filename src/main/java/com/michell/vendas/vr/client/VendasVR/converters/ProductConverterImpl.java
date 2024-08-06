package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverterImpl implements ProductConverter {

    @Override
    public ProductEntity converter(ProductEntity entity, ProductDTO dto){
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    @Override
    public ProductDTO converter(ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    @Override
    public List<ProductDTO> converterToListDto(List<ProductEntity> productsEntities){
        List<ProductDTO> productResponseDTO = new ArrayList<>();
        for (ProductEntity productEntity : productsEntities){
            productResponseDTO.add(converter(productEntity));
        }
        return productResponseDTO;
    }

    @Override
    public ProductEntity converter(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    @Override
    public List<ProductEntity> converter(List<ProductDTO> productsDTOs){
        List<ProductEntity> productsEntities = new ArrayList<>();
        for (ProductDTO productDTO : productsDTOs){
            productsEntities.add(converter(productDTO));
        }
        return productsEntities;
    }


}
