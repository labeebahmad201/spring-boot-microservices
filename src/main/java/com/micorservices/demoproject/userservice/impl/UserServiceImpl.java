package com.micorservices.demoproject.userservice.impl;

import com.micorservices.demoproject.shared.Utils;
import com.micorservices.demoproject.ui.model.request.UserDetailModelRequest;
import com.micorservices.demoproject.ui.model.request.UserDetailUpdateRequest;
import com.micorservices.demoproject.ui.model.response.UserRest;
import com.micorservices.demoproject.userservice.UserService;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private HashMap<String, UserRest> users = new HashMap<String, UserRest>(); 

    private Utils utils;

    public void UserServiceImp(){}

    /***
     * 
     * the followin construtor shows how to do constructor based dependency injection. 
     */
    @Autowired
    public void UserServiceImp(Utils utils){
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailModelRequest userDetailModelRequest) {

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetailModelRequest.getEmail());
        returnValue.setFirstName(userDetailModelRequest.getFirstName());
        returnValue.setLastName(userDetailModelRequest.getLastName());
        returnValue.setUserId(this.utils.generateRandomString());

        if( this.users.get(returnValue.getUserId()) == null ){
            this.users.put(returnValue.getUserId(), returnValue);
        }

        return returnValue;
    }

    @Override
    public UserRest updateUser(UserDetailUpdateRequest userDetailUpdateRequest, String userId)  {
        
        UserRest userRest = this.users.get(userId);
        if(userRest == null){
            return null;
        }
        userRest.setFirstName(userDetailUpdateRequest.getFirstName());
        userRest.setLastName(userDetailUpdateRequest.getLastName());
        UserRest updatedUserRest = this.users.put(userId, userRest);
        return userRest;        
    }
    

    @Override
    public UserRest getUser(String userId) {
        return this.users.get(userId);
    }

    @Override
    public UserRest deleteUser(String userId) {
        UserRest userRest = this.users.get(userId);        
        if(userRest == null){
            return userRest;
        }
        this.users.remove(userId);
        return userRest;
    }
}
