package com.dataart.citybikerentalservicespring.view.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by mkrasowski on 16.09.2016.
 */
public class PaymentRequest {
    @NotNull
    private Integer idUser;
    @NotNull(message = "No card number provided.")
    @Pattern(regexp = "((?=.*\\d).{16})", message = "Incorrect card number! Should contain 16 number characters.")
    private String cardNo;
    @NotNull(message = "No expiration date provided.")
//    There is nice annotation for checking the Instant date objects for being in the past or the future
//    but the problem is the jackson library doesn't parse the Instants yet. Maybe I could work it around
//    using some parsing on the method level and annotate the method
    private String cardExpirationDate;
    @NotNull(message = "No cvv card code provided.")
    @Pattern(regexp = "((?=.*\\d).{3})", message = "Cvv code should have 3 number characters.")
    private String cvvCode;
    @NotNull(message = "Provide amount to add to account!")
    @Min(value = 0, message = "Value must be greater than zero!")
    private BigDecimal amount;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer idUser, BigDecimal amount, String cardNo, String cardExpirationDate, String cvvCode) {
        this.idUser = idUser;
        this.amount = amount;
        this.cardNo = cardNo;
        this.cardExpirationDate = cardExpirationDate;
        this.cvvCode = cvvCode;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
