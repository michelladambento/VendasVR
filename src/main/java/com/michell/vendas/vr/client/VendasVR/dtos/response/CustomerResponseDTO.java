package com.michell.vendas.vr.client.VendasVR.dtos.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Long id;

    private String customerName;

    private Double purchaseLimit;

    private LocalDate closingDateAt;



}
