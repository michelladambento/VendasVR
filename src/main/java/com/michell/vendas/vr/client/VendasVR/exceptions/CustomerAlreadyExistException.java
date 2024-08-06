package com.michell.vendas.vr.client.VendasVR.exceptions;

import lombok.Generated;

@Generated
public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(String message){
        super(message);
    }
}
