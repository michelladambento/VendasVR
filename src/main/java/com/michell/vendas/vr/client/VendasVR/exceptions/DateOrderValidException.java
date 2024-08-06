package com.michell.vendas.vr.client.VendasVR.exceptions;

import lombok.Generated;

import java.time.LocalDate;

@Generated
public class DateOrderValidException extends RuntimeException {

    private static final String NOT_FOUND_MESSAGE = "Data do fechamento: (%s) está vencida, altere no cadastro de cliente. ";

    public DateOrderValidException(LocalDate date){
        super(String.format(NOT_FOUND_MESSAGE, date));
    }
}
