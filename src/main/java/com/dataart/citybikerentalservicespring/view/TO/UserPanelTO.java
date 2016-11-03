package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.Payment;
import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.utils.WebUtil;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class UserPanelTO {
    private BigDecimal userBalance;
    private BigDecimal paymentMoney;
    private Integer bikeId;
    private Instant beginTime;
    private Long serverSeconds;


    public UserPanelTO() {
    }

    public UserPanelTO(User user, RentalHistory rentalHistory, Payment payment) {
        if (rentalHistory == null) {
            this.beginTime = null;
            this.serverSeconds = null;
        } else {
            this.beginTime = rentalHistory.getBeginTime();
            this.serverSeconds = calculateServerSeconds();
        }
        this.userBalance = user.getBalance();
        if (user.getBike() == null) {
            this.bikeId = null;
        } else {
            this.bikeId = user.getBike().getId();
        }
        if (payment == null) {
            this.paymentMoney = null;
        } else {
            this.paymentMoney = payment.getMoney();
        }
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

    public Long getServerSeconds() {
        return serverSeconds;
    }

    public void setServerSeconds(Long serverSeconds) {
        this.serverSeconds = serverSeconds;
    }

    public long calculateServerSeconds() {
        return WebUtil.calculateSecPassed(beginTime);
//        return 0;
    }
}
