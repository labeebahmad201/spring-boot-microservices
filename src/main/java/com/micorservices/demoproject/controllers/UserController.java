package com.micorservices.demoproject.controllers;

import java.util.HashMap;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micorservices.demoproject.ui.model.request.UserDetailModelRequest;
import com.micorservices.demoproject.ui.model.request.UserDetailUpdateRequest;
import com.micorservices.demoproject.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

    HashMap<String, UserRest> users = new HashMap<String, UserRest>(); 

    @GetMapping
    public String getUsers(@RequestParam(value="page") int page, @RequestParam(value="limit", defaultValue = "10") int limit){
        // you can make request parameters optional using defaultValue.... you can also use 
        // required = false this doesn't work well with primitive types cause those can't be converted to null. 
        return "get users called page = " + page + " limit " + limit;
    }

    @GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){
        UserRest returnValue = this.users.get(userId);
        if(returnValue == null){
            return new ResponseEntity<UserRest>(returnValue, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PostMapping(
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        },
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        }        
    )
    public UserRest createUser(@Valid @RequestBody UserDetailModelRequest userDetailModelRequest){

        String firstname = null;
        int len = firstname.length();

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetailModelRequest.getEmail());
        returnValue.setFirstName(userDetailModelRequest.getFirstName());
        returnValue.setLastName(userDetailModelRequest.getLastName());
        returnValue.setUserId(UUID.randomUUID().toString());

        if( this.users.get(returnValue.getUserId()) == null ){
            this.users.put(returnValue.getUserId(), returnValue);
        }
        return returnValue;
    }

    @PutMapping(
        value = "/{userId}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        },
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        }        
    )
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UserDetailUpdateRequest userDetailUpdateRequest){
        UserRest  userRest = this.users.get(userId);

        if(userRest == null){
            return new ResponseEntity<UserRest>(HttpStatus.NOT_FOUND);
        }

        userRest.setFirstName(userDetailUpdateRequest.getFirstName());
        userRest.setLastName(userDetailUpdateRequest.getLastName());
        UserRest updatedUserRest = this.users.put(userId, userRest);
        return new ResponseEntity<UserRest>(updatedUserRest, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        UserRest userRest = this.users.get(userId);
        if(userRest == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.users.remove(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
