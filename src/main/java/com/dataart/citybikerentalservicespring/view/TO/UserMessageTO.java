package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.constants.UserMessage;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class UserMessageTO {
    private UserMessage userMessage;

    public UserMessageTO(UserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }
}
