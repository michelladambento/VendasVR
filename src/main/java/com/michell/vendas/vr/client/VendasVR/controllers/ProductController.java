package com.michell.vendas.vr.client.VendasVR.controllers;

import com.michell.vendas.vr.client.VendasVR.converters.ProductConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllCustomersDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.RetrieveAllProductsDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> saveCustomer(@RequestBody ProductDTO productDTO){
        productService.saveProduct(productDTO);
        ResponseDTO response = new ResponseDTO(true, "Produto salvo com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<RetrieveAllProductsDTO> findAllProducts(){
        List<ProductEntity> productsEntities = productService.findAllProducts();
        List<ProductDTO> customersResponse = productConverter.converter(productsEntities);
        RetrieveAllProductsDTO response = new RetrieveAllProductsDTO(true, "Produtos recuperado com sucesso.");
        response.setProducts(customersResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        ResponseDTO response = new ResponseDTO(true, "Produto deletado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        Long productId = productDTO.getId();
        ProductEntity productEntity = productService.findById(productId);
        productService.updateProduct(productEntity, productDTO);
        ResponseDTO response = new ResponseDTO(true, "Produto atualizado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
