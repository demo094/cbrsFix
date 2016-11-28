package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUser;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.view.TO.UserDetailsTO;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class PaymentResponse {
    private int userId;

    public PaymentResponse(AuthenticatedUser authenticatedUser) {
        this.userId = authenticatedUser.getId();
    }

    public PaymentResponse(UserDetailsTO userDetailsTO) {
        this.userId = userDetailsTO.getId();
    }

    public PaymentResponse(User user) {
        this.userId = user.getId();
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
