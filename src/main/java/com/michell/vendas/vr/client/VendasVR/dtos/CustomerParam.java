package com.michell.vendas.vr.client.VendasVR.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Generated
public class CustomerParam {

    private Long id;

    private String customerName;

    private Double purchaseLimit;

    private LocalDate closingDateAt;

}
