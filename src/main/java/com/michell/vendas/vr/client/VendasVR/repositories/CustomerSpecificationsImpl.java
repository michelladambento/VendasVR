package com.michell.vendas.vr.client.VendasVR.repositories;

import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecificationsImpl implements CustomerSpecifications{
    @Override
    public Specification<CustomerEntity> hasCustomerName(String customerName) {
        return (customer, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(customer.get("customerName"), customerName);
    }
}
