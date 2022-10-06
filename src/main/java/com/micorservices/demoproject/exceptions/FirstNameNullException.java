package com.micorservices.demoproject.exceptions;

public class FirstNameNullException extends RuntimeException {
    private String message;

    public FirstNameNullException(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
