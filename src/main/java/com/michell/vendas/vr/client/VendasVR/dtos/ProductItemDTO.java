package com.michell.vendas.vr.client.VendasVR.dtos;

import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.entities.PurchaserOrderEntity;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated
public class ProductItemDTO {

    private Long id;

    private PurchaseOrderDTO purchaserOrder;

    private ProductDTO product;

    private Integer qtd;

    private Double unitPrice;

}
