package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions.JsonWebTokenException;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.view.TO.UserDetailsTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by mkrasowski on 18.10.2016.
 */
@Component
public class JwtHelper {
    @Value("${secret}")
    private String secret;

    public UserDetailsTO parseToken(String token) throws JsonWebTokenException {
        try {
            if(token.isEmpty()){
                return null;
            } else {
                Claims body = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();

                UserDetailsTO userDetailsTO = new UserDetailsTO();
                userDetailsTO.setEmail(body.getSubject());
                userDetailsTO.setId(Integer.parseInt((String) body.get("id")));
                userDetailsTO.setRoles((List<String>) body.get("userRoleList"));
                userDetailsTO.setTokenIssueTime(Instant.ofEpochMilli((Long) body.get("issueTime")));

                return userDetailsTO;
            }
        } catch (JwtException | ClassCastException ex) {
            throw new JsonWebTokenException(ex);
        }
    }

    public String generateToken(User user) {
        UserDetailsTO userDetailsTO = new UserDetailsTO(user);
        Claims claims = Jwts.claims();
        claims.setSubject(userDetailsTO.getEmail());
        claims.put("id", userDetailsTO.getId() + "");
        claims.put("userRoleList", userDetailsTO.getRoles());
        claims.put("issueTime", new Date());

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
