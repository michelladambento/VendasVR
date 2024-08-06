package com.michell.vendas.vr.client.VendasVR.dtos.response;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Generated
public class RetrieveAllCustomersDTO extends ResponseDTO{

    private List<CustomerDTO> customers;

    public RetrieveAllCustomersDTO(boolean success, String detail){
        super(success, detail);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderDTO {

        private Long id;

        private LocalDate orderDateAt;

        private CustomerDTO customer;

        private Double totalOrder;

    }
}
