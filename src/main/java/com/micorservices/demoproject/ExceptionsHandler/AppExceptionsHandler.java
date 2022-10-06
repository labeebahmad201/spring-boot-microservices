package com.micorservices.demoproject.ExceptionsHandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.micorservices.demoproject.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler {

    /* 
    /**
     * handleAnyException can be any name.
     */
    /*
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
        return new ResponseEntity<>(ex.getStackTrace(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    */

    /**
     * handles null pointer exception
     */
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
