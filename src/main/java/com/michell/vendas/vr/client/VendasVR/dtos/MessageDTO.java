package com.michell.vendas.vr.client.VendasVR.dtos;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Generated
public class MessageDTO implements Serializable {

    private boolean success;

    private String details;

    public MessageDTO(boolean success, String details){
        this.success = success;
        this.details = details;
    }

}
