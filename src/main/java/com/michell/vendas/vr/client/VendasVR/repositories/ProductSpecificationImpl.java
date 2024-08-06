package com.michell.vendas.vr.client.VendasVR.repositories;

import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationImpl implements ProductSpecification {
    @Override
    public Specification<ProductEntity> hasDescription(String description) {
        return (product, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(product.get("description"), description);
    }
}
