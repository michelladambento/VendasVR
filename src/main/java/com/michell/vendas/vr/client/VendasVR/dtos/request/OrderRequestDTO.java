package com.michell.vendas.vr.client.VendasVR.dtos.request;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {

    private Long id;

    private LocalDate orderDateAt;

    private CustomerDTO customer;

    private Double totalOrder;

}
