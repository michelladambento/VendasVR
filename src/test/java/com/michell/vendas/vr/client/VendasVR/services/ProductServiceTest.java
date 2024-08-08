package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.controllers.CustomerController;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {


    @Mock
    private ProductRepository repository;

    @Mock
    private ProductSpecification specification;

    @InjectMocks
    private ProductService productService;

    private ProductDTO productDTO;

    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDTO = new ProductDTO();
        productDTO.setDescription("Detergente");
        productDTO.setPrice(100.0);

        productEntity = new ProductEntity();
        productEntity.setDescription("Detergente");
        productEntity.setPrice(100.0);
    }

    @Test
    public void saveProductSusccessfully(){
        Specification<ProductEntity> mockSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("description"), productDTO.getDescription());
        when(specification.hasDescription(productDTO.getDescription())).thenReturn(mockSpecification);
        when(repository.findOne(mockSpecification)).thenReturn(Optional.empty());
        productService.saveProduct(productDTO);
        verify(repository).findOne(mockSpecification);
        verify(repository).saveAndFlush(Mockito.any(ProductEntity.class)); // Verify save was called
    }






}
