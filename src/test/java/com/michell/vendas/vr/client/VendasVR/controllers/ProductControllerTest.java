package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.converters.ProductConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.MessageDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllCustomersDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllProductsDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;

    @Mock
    private ProductConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveProductSuccessfully(){
        ProductDTO dto = new ProductDTO();
        doNothing().when(service).saveProduct(dto);
        ResponseEntity<ResponseDTO> response = controller.saveProduct(dto);
        ResponseDTO body = response.getBody();
        MessageDTO message = body.getMessage();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(message.isSuccess());
        assertEquals("Produto salvo com sucesso.", message.getDetails());
        verify(service).saveProduct(dto);
    }

    @Test
    public void retrieveAllproductsSuccessfully(){
        List<ProductEntity> productsEntities = new ArrayList<>();
        List<ProductDTO> productsDtos = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity();
        ProductDTO productDTO = new ProductDTO();
        productsEntities.add(productEntity);
        productsDtos.add(productDTO);

        when(service.findAllProducts()).thenReturn(productsEntities);
        when(converter.converterToListDto(productsEntities)).thenReturn(productsDtos);

        ResponseEntity<RetrieveAllProductsDTO> response = controller.findAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getMessage().isSuccess());
        assertEquals("Produtos recuperado com sucesso.", response.getBody().getMessage().getDetails());
        assertNotNull(response.getBody().getProducts());
        assertEquals(1, response.getBody().getProducts().size());
        verify(service).findAllProducts();
        verify(converter).converterToListDto(productsEntities);
    }

    @Test
    public void deleteProductSuccessfully(){
        Long id = 1L;
        doNothing().when(service).deleteProduct(id);
        ResponseEntity<ResponseDTO> response = controller.deleteProduct(id);
        MessageDTO message = response.getBody().getMessage();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(message.isSuccess());
        assertEquals("Produto deletado com sucesso.", message.getDetails());
        verify(service).deleteProduct(id);
    }

    @Test
    public void updateProductSuccessfully(){
        ProductDTO dto = new ProductDTO();
        ProductEntity entity = new ProductEntity();
        dto.setId(1L);
        Long id = dto.getId();
        entity.setId(id);
        when(service.findById(id)).thenReturn(entity);
        doNothing().when(service).updateProduct(entity, dto);
        ResponseEntity<ResponseDTO> response = controller.updateProduct(dto);
        MessageDTO message = response.getBody().getMessage();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(message.isSuccess());
        assertEquals("Produto atualizado com sucesso.", message.getDetails());
        verify(service).findById(id);
        verify(service).updateProduct(entity, dto);
    }

}
