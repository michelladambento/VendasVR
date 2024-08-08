package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.controllers.CustomerController;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultAlreadyExistException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultNotFoundException;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void throwsExceptionWhenProductExists() {
        Specification<ProductEntity> mockSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("description"), productDTO.getDescription());

        when(specification.hasDescription(productDTO.getDescription())).thenReturn(mockSpecification);
        when(repository.findOne(mockSpecification)).thenReturn(Optional.of(productEntity)); // Product exists

        DefaultAlreadyExistException exception = assertThrows(DefaultAlreadyExistException.class, () -> {
            productService.saveProduct(productDTO);
        });

        String message = String.format("Produto (%s) já existe", productDTO.getDescription());
        assertEquals(message, exception.getMessage());

        verify(repository).findOne(mockSpecification);
        verify(repository, never()).saveAndFlush(any(ProductEntity.class)); //parametro never é pra especificar que o metodo nunca foi chamado.
    }

    @Test
    public void allProductsFounded(){
        List<ProductEntity> list = new ArrayList<>();
        list.add(productEntity);
        when(repository.findAll()).thenReturn(list);
        List<ProductEntity> allProducts = productService.findAllProducts();
        assertFalse(allProducts.isEmpty());
        verify(repository).findAll();
    }

//    public void deleteProduct(Long id){
//        if (productRepository.existsById(id)) {
//            productRepository.deleteById(id);
//        } else {
//            String message = String.format("Produto ID:(%s) não encontrado.", id);
//            throw new DefaultNotFoundException(message);
//        }
//    }
    @Test
    public void productDeletedSuccessfully(){
        when(repository.existsById(Mockito.any())).thenReturn(true);
        productService.deleteProduct(productEntity.getId());
        verify(repository).deleteById(Mockito.any());
    }

    @Test
    public void productNotFoundThrowException(){
        when(repository.existsById(Mockito.any())).thenReturn(false);

        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, ()->{
            productService.deleteProduct(productEntity.getId());
        });
        verify(repository).existsById(Mockito.any());
        verify(repository, never()).deleteById(Mockito.any());
    }
}
