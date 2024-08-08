package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductConverterTest {

    @Autowired
    private ProductConverter converter;

    @Test
    public void convertedToEntityProvidedEntityAndDto(){
        ProductDTO dto = loadProductDTO();
        ProductEntity entity = new ProductEntity();
        ProductEntity entityUpdated = converter.converter(entity, dto);
        assertSame(entity, entityUpdated);
    }

    @Test
    public void convertProductDtoToEntitySuccessfully(){
        ProductDTO productDTO = loadProductDTO();
        ProductEntity productEntity = converter.converter(productDTO);
        assertEquals(productDTO.getId(), productEntity.getId());
        assertEquals(productDTO.getDescription(), productEntity.getDescription());
        assertEquals(productDTO.getPrice(), productEntity.getPrice());
    }

    @Test
    public void convertListProductDtoToListEntitySuccessfully(){
        List <ProductDTO> listDTOS = new ArrayList<>();
        ProductDTO productDTO = loadProductDTO();
        listDTOS.add(productDTO);
        List<ProductEntity> productEntity = converter.converter(listDTOS);
        assertFalse(productEntity.isEmpty());
    }

    @Test
    public void convertProductEntityToDtoSuccessfully(){
        ProductEntity productEntity = loadProductEntity();
        ProductDTO productDTO = converter.converter(productEntity);
        assertEquals(productDTO.getId(), productEntity.getId());
        assertEquals(productDTO.getDescription(), productEntity.getDescription());
        assertEquals(productDTO.getPrice(), productEntity.getPrice());
    }

    @Test
    public void convertListProductEntityToListDtoSuccessfully(){
        List <ProductEntity> listEntities = new ArrayList<>();
        ProductEntity productEntity = loadProductEntity();
        listEntities.add(productEntity);
        List<ProductDTO> productDtos = converter.converterToListDto(listEntities);
        assertFalse(productDtos.isEmpty());
    }


    private ProductEntity loadProductEntity(){
        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setDescription("Arroz Zorzo");
        entity.setPrice(22.30);
        return entity;
    }

    private ProductDTO loadProductDTO(){
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setDescription("Arroz Zorzo");
        dto.setPrice(22.30);
        return dto;
    }
}
