package com.michell.vendas.vr.client.VendasVR.repositories;

import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaserOrderRepository extends JpaRepository<PurchaserOrderEntity, Long>, JpaSpecificationExecutor<PurchaserOrderEntity> {
}
