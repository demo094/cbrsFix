package com.dataart.citybikerentalservicespring.view.requests;

import com.dataart.citybikerentalservicespring.utils.annotations.NotExpired;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Created by mkrasowski on 16.09.2016.
 */
public class PaymentRequest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-yyyy");

    @NotNull
    private Integer idUser;
    @NotEmpty(message = "No card number provided.")
    @Pattern(regexp = "((?=.*\\d).{16})", message = "Incorrect card number! Should contain 16 number characters.")
    private String cardNo;
    @NotEmpty(message = "No expiration date provided.")
    @NotExpired(message = "This card has expired!")
    private String cardExpirationDate;
    @NotEmpty(message = "No cvv card code provided.")
    @Pattern(regexp = "((?=.*\\d).{3})", message = "Cvv code should have 3 number characters.")
    private String cvvCode;
    @NotNull(message = "Provide an amount to add!")
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
