package com.michell.vendas.vr.client.VendasVR.exceptions;

import lombok.Generated;

import java.time.LocalDate;

@Generated
public class TotalOrderValidException extends RuntimeException {

    private static final String NOT_FOUND_MESSAGE = "Limite disponível:RS(%s) - Data de fechamento: (%s).";

    public TotalOrderValidException(Double purchaseLimit, LocalDate date){
        super(String.format(NOT_FOUND_MESSAGE, purchaseLimit, date));
    }
}
