package com.micorservices.demoproject.userservice;
import com.micorservices.demoproject.ui.model.request.UserDetailModelRequest;
import com.micorservices.demoproject.ui.model.request.UserDetailUpdateRequest;
import com.micorservices.demoproject.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailModelRequest userDetailModelRequest);    
    UserRest updateUser(UserDetailUpdateRequest userDetailUpdateRequest, String userId);
    UserRest getUser(String userId);
    UserRest deleteUser(String userId);
}
