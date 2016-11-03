package com.dataart.citybikerentalservicespring.view.requests;

/**
 * Created by mkrasowski on 08.09.2016.
 */
public class RegistrationRequest {
    private String email;
    private String password;
    private String retypedPassword;

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
