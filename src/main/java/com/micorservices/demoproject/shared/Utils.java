package com.micorservices.demoproject.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Utils {
    public String generateRandomString(){
        return UUID.randomUUID().toString();
    }
}
