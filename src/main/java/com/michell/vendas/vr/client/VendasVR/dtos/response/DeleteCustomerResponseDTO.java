package com.michell.vendas.vr.client.VendasVR.dtos.response;

import com.michell.vendas.vr.client.VendasVR.dtos.MessageDTO;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Generated
public class DeleteCustomerResponseDTO implements Serializable {

    private MessageDTO message;

    public DeleteCustomerResponseDTO(boolean success, String details) {
        this.message = new MessageDTO(success, details);
    }

}
