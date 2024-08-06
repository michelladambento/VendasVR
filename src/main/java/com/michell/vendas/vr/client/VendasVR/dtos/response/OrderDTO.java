package com.michell.vendas.vr.client.VendasVR.dtos.response;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private LocalDate orderDateAt;

    private CustomerDTO customer;

    private Double totalOrder;

}
