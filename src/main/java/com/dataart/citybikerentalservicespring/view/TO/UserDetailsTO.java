package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.persistence.model.UserRole;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mkrasowski on 19.10.2016.
 */
public class UserDetailsTO {
    private Integer id;
    private String email;
    private List<String> roles;
    private Instant tokenIssueTime;

    public UserDetailsTO() {
    }

    public UserDetailsTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = getRolesFromUser(user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Instant getTokenIssueTime() {
        return tokenIssueTime;
    }

    public void setTokenIssueTime(Instant tokenIssueTime) {
        this.tokenIssueTime = tokenIssueTime;
    }

    private List<String> getRolesFromUser(User user){
        return user.getUserRoleList().stream().map(UserRole::getName).collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
