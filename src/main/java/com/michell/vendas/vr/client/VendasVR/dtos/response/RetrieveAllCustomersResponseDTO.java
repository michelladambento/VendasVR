package com.michell.vendas.vr.client.VendasVR.dtos.response;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.MessageDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Generated
public class RetrieveAllCustomersResponseDTO extends ResponseDTO{

    private List<CustomerDTO> customers;

    public RetrieveAllCustomersResponseDTO(boolean success, String detail){
        super(success, detail);
    }

}
