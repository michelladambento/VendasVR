package com.michell.vendas.vr.client.VendasVR.repositories;

import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public interface ProductSpecification {

    Specification<ProductEntity> hasDescription(String description);

}
