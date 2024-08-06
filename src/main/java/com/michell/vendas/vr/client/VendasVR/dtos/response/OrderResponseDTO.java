package com.michell.vendas.vr.client.VendasVR.dtos.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long id;

    private LocalDate orderDateAt;

    private CustomerResponseDTO customer;

    private Double totalOrder;

}
