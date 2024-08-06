package com.michell.vendas.vr.client.VendasVR.dtos;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Generated
public class ResponseDTO implements Serializable {

    public MessageDTO message;

    public ResponseDTO(){}

    public ResponseDTO(MessageDTO message){
        this.message = message;
    }

    public ResponseDTO(boolean success, String details){
        this.message = new MessageDTO(success, details);
    }
}
