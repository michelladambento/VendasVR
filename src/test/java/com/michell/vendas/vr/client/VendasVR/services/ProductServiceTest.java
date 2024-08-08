package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.controllers.CustomerController;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    private ProductRepository repository;

    @Mock
    private ProductSpecification specification;

    @InjectMocks
    private ProductService service;

    private ProductDTO productDTO;

    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
        productDTO.setDescription("Test Product");
        productDTO.setPrice(100.0);

        productEntity = new ProductEntity();
        productEntity.setDescription("Test Product");
        productEntity.setPrice(100.0);
    }

    @Test
    public void saveProductSusccessfully(){
        // Configure the mock behavior
        when(productRepository.findOne(any())).thenReturn(Optional.empty());
        when(productRepository.saveAndFlush(any(ProductEntity.class))).thenReturn(productEntity);

        // Call the method under test
        service.saveProduct(productDTO);

        // Verify interactions
        verify(productRepository, times(1)).findOne(any());
        verify(productRepository, times(1)).saveAndFlush(any(ProductEntity.class));


        when(specification.hasDescription(dto.getDescription())).thenReturn(Specification< ProductEntity > teste);
    }



}
