package com.dataart.citybikerentalservicespring.view.requests;

/**
 * Created by mkrasowski on 18.10.2016.
 */
public class AuthenticationRequest {

    private String email;
    private String password;

    public AuthenticationRequest() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
