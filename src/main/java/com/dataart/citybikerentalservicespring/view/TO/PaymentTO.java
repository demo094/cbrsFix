package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUser;
import com.dataart.citybikerentalservicespring.persistence.model.User;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class PaymentTO {
    private int userId;

    public PaymentTO(AuthenticatedUser authenticatedUser) {
        this.userId = authenticatedUser.getId();
    }

    public PaymentTO(UserDetailsTO userDetailsTO) {
        this.userId = userDetailsTO.getId();
    }

    public PaymentTO(User user) {
        this.userId = user.getId();
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
