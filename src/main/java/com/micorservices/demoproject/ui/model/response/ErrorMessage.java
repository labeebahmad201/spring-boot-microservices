package com.micorservices.demoproject.ui.model.response;

import java.util.Date;

public class ErrorMessage {
    Date timestamp;
    String message;
    

    public ErrorMessage(Date timestamp, String message){
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
