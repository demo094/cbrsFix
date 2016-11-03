package com.dataart.citybikerentalservicespring.view.requests;

import java.math.BigDecimal;

/**
 * Created by mkrasowski on 16.09.2016.
 */
public class PaymentRequest {
    private Integer idUser;
//    private String cardNo;
//    private String cardExpirationDate;
//    private String cvvCode;
    private BigDecimal amount;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer idUser, BigDecimal amount) {
        this.idUser = idUser;
        this.amount = amount;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

//    public String getCardNo() {
//        return cardNo;
//    }
//
//    public void setCardNo(String cardNo) {
//        this.cardNo = cardNo;
//    }
//
//    public String getCardExpirationDate() {
//        return cardExpirationDate;
//    }
//
//    public void setCardExpirationDate(String cardExpirationDate) {
//        this.cardExpirationDate = cardExpirationDate;
//    }
//
//    public String getCvvCode() {
//        return cvvCode;
//    }
//
//    public void setCvvCode(String cvvCode) {
//        this.cvvCode = cvvCode;
//    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
