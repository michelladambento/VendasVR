package com.michell.vendas.vr.client.VendasVR.exceptions;

import lombok.Generated;

@Generated
public class CustomerNotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MESSAGE = "Cliente (%s) não encontrado.";

    public CustomerNotFoundException(Long customerId){
        super(String.format(NOT_FOUND_MESSAGE, customerId));
    }
}
