package com.michell.vendas.vr.client.VendasVR.exceptions;

import com.michell.vendas.vr.client.VendasVR.dtos.MessageDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Generated
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= { DefaultNotFoundException.class, DateOrderValidException.class, TotalOrderValidException.class, DefaultAlreadyExistException.class })
    public ResponseEntity<Object> handleCustomerNotFoundException(Exception ex, WebRequest request){
        return internalServerError(ex);
    }

    private ResponseEntity<Object> notFound(RuntimeException ex){
        String errorMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return createResponseEntity(errorMessage, httpStatus);
    }

    private ResponseEntity<Object> internalServerError(Exception ex){
        String errorMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return createResponseEntity(errorMessage, httpStatus);
    }

    private ResponseEntity<Object> createResponseEntity(String errorMessage, HttpStatus httpsStatus){
        MessageDTO messageDto = new MessageDTO(false, errorMessage);
        ResponseDTO responseDTO = new ResponseDTO(messageDto);
        return new ResponseEntity<>(responseDTO, httpsStatus);
    }

}
