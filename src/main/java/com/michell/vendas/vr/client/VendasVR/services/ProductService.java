package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultNotFoundException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultAlreadyExistException;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSpecification productSpecifications;

    public void saveProduct(ProductDTO dto){
        checkProductExist(dto.getDescription());
        ProductEntity productEntity = createProductEntity(dto);
        productRepository.saveAndFlush(productEntity);
    }

    private void checkProductExist(String description){
        Specification<ProductEntity> hasCustomer = productSpecifications.hasDescription(description);
        Optional<ProductEntity> optProduct = productRepository.findOne(hasCustomer);
        if(optProduct.isPresent()){
            String message = String.format("Produto (%s) já existe", description);
            throw new DefaultAlreadyExistException(message);
        }
    }

    private ProductEntity createProductEntity(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;

    }

    public List<ProductEntity> findAllProducts(){
        return productRepository.findAll();
    }

    public ProductEntity findById(Long productId){
        Optional<ProductEntity> optProductEntity = productRepository.findById(productId);
        if(!optProductEntity.isPresent()){
            String message = String.format("Produto ID:(%s) não encontrado.", productId);
            throw new DefaultNotFoundException(message);
        }
        return optProductEntity.get();
    }

    public void deleteProduct(Long id){
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            String message = String.format("Produto ID:(%s) não encontrado.", id);
            throw new DefaultNotFoundException(message);
        }
    }

    public void updateProduct(ProductEntity entity, ProductDTO dto ) {
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        productRepository.saveAndFlush(entity);
    }
}
