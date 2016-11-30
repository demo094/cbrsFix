package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.service.LoginService;
import com.dataart.citybikerentalservicespring.view.requests.AuthenticationRequest;
import com.dataart.citybikerentalservicespring.view.responses.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mkrasowski on 30.11.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class SecurityController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse userAuthentication(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        response.addCookie(new Cookie("accessToken", loginService.createAuthenticationToken(authenticationRequest)));
        return new CommonResponse("Login ok!");
    }
}
