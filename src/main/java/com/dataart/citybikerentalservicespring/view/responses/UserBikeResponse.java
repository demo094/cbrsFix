package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.User;

/**
 * Created by mkrasowski on 27.09.2016.
 */
public class UserBikeResponse {

    private Integer userId;
    private Integer bikeId;

    public UserBikeResponse(User user) {
        this.userId = user.getId();
        if(user.getBike() != null){
            this.bikeId = user.getBike().getId();
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }
}
