package com.dataart.citybikerentalservicespring.persistence.model;

import com.dataart.citybikerentalservicespring.constants.TokenType;
import com.dataart.citybikerentalservicespring.utils.WebUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mkrasowski on 15.09.2016.
 */

@NamedQueries(
        {@NamedQuery(
                name = Token.FIND_BY_BODY,
                query = "from Token where body = :body"
        )}
)
@Entity
@Table(name = "token")
public class Token {

    public static final String FIND_BY_BODY = "Token.FIND_BY_BODY";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String body;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @Column(name = "token_type", nullable = false)
    private TokenType type;

    public Token() {
    }

    public Token(TokenType tokenType, String body, User user) {
        this.body = body;
        this.type = tokenType;
        this.user = user;
        this.expirationDate = WebUtil.calculateTokenExpiryDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Enumerated(EnumType.STRING)
    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
}
