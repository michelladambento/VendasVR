package com.michell.vendas.vr.client.VendasVR.dtos;

import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Generated
public class PurchaseOrderDTO {

    private CustomerDTO customer;

    private LocalDate orderDateAt;

    private Double orderTotal;

    private List<ProductItemDTO> productItens;
}
