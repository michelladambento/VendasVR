package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;

import java.util.List;

public interface ProductItemConverter {

    ProductItemEntity converter(ProductItemDTO dto);
    List<ProductItemEntity> converter(List<ProductItemDTO> productItensDTOS);
}
