package com.dataart.citybikerentalservicespring.persistence.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by mkrasowski on 06.09.2016.
 */


@Entity
@Table(name = "user")
public class User{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String email;
    @Column(name="password_hash", nullable = false)
    private String passwordHash;
    @Column(name = "create_time", nullable = false)
    private Date createTime;
    @Column(nullable = false)
    private BigDecimal balance;
    @OneToOne(targetEntity = Bike.class)
    @JoinColumn(name = "rented_bike_id")
    private Bike bike;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(nullable = false)
    private boolean activated;
    @ManyToMany(targetEntity = UserRole.class, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRole> userRoleList;


    public User(){}

    public User(String email, String passwordHash, Date createTime, List<UserRole> roles) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.createTime = createTime;
        this.userRoleList = roles;
        this.balance = BigDecimal.ZERO;
        this.activated = false;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }
}
