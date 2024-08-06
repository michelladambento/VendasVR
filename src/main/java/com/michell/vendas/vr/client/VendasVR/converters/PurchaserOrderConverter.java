package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.PurchaseOrderDTO;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;

public interface PurchaserOrderConverter {
    PurchaserOrderEntity converter(PurchaseOrderDTO dto);
}
