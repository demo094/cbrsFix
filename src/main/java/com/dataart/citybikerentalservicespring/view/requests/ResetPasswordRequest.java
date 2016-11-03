package com.dataart.citybikerentalservicespring.view.requests;

/**
 * Created by mkrasowski on 28.09.2016.
 */
public class ResetPasswordRequest {
    private String email;
    private String password;
    private String passwordRetype;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRetype() {
        return passwordRetype;
    }
}
