package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;

import java.util.ArrayList;
import java.util.List;

public interface ProductConverter {

    ProductEntity converter(ProductEntity entity, ProductDTO dto);

    ProductDTO converter(ProductEntity entity);

    List<ProductDTO> converterToListDto(List<ProductEntity> productsEntities);

    ProductEntity converter(ProductDTO dto);

    List<ProductEntity> converter(List<ProductDTO> productsDTOs);

}
