package com.michell.vendas.vr.client.VendasVR.repositories;

import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


public interface CustomerSpecifications {

    Specification<CustomerEntity> hasCustomerName(String customerName);

}
