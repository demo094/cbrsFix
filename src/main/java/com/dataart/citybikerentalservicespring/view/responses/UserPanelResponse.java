package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.Payment;
import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class UserPanelResponse {
    private BigDecimal userBalance;
    private BigDecimal paymentMoney;
    private Integer bikeId;
    private Long serverTime;
    private List<String> roles;

    public UserPanelResponse() {
    }

    public UserPanelResponse(User user, RentalHistory rentalHistory, Payment payment, List<GrantedAuthority> authorities) {
        if (rentalHistory != null) {
            this.serverTime = rentalHistory.getBeginTime().toEpochMilli();
        }
        this.userBalance = user.getBalance();
        if (user.getBike() != null) {
            this.bikeId = user.getBike().getId();
        }
        if (payment != null) {
            this.paymentMoney = payment.getMoney();
        }
        this.roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

    public BigDecimal getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(BigDecimal paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
