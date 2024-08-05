package com.michell.vendas.vr.client.VendasVR.dtos.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {

    private Long id;

    private String customerName;

    private Double purchaseLimit;

    private LocalDate closingDateAt;

}
