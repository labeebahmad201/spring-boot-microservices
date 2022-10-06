package com.micorservices.demoproject.ui.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDetailUpdateRequest {
    @NotBlank
    @Size(min = 6, message = "first name length should be greater that 5")
    private String firstName;
    @NotBlank
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
