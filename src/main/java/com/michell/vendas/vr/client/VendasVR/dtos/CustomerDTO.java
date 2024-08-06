package com.michell.vendas.vr.client.VendasVR.dtos;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Generated
public class CustomerDTO {

    private Long id;

    private String customerName;

    private Double purchaseLimit;

    private LocalDate closingDateAt;

}
