package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.InvalidTokenException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.UserNotFoundException;
import com.dataart.citybikerentalservicespring.persistence.model.Token;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.service.LoginService;
import com.dataart.citybikerentalservicespring.service.UserService;
import com.dataart.citybikerentalservicespring.view.requests.AuthenticationRequest;
import com.dataart.citybikerentalservicespring.view.requests.RegistrationRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResendTokenRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResetPasswordRequest;
import com.dataart.citybikerentalservicespring.view.responses.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.stream.Stream;

/**
 * Created by mkrasowski on 30.11.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class SecurityController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse userAuthentication(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        response.addCookie(new Cookie("accessToken", loginService.createAuthenticationToken(authenticationRequest)));
        return new CommonResponse("Login ok!");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResponse userLogout(HttpServletRequest request, HttpServletResponse response){
        Stream.of(request.getCookies())
                .filter(cookie -> cookie.getName().equals("accessToken"))
                .findAny()
                .ifPresent(cookie -> cookie.setValue(""));
        response.addCookie(new Cookie("accessToken", ""));
        return new CommonResponse("Logout ok");
    }

    @RequestMapping(value = "/registrationConfirm/{token}", method = RequestMethod.GET)
    public CommonResponse confirmationPage(@PathVariable("token") String token) throws CbrsException {
        Token storedToken = userService.findTokenByBody(token);
        if (storedToken == null) {
            throw new InvalidTokenException();
        }
        User user = storedToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (storedToken.getExpirationDate().getTime() - calendar.getTime().getTime() <= 0) {
            return new CommonResponse("Token has expired!");
        } else {
            userService.setUserActivated(user, true);
            return new CommonResponse("Account activated. You can now log in.");
        }
    }

    @RequestMapping(value = "/resetPassEmail", method = RequestMethod.POST)
    public CommonResponse resetPassword(@RequestBody ResendTokenRequest resendTokenRequest) {
        userService.sendResetPasswordEmail(resendTokenRequest.getEmail());
        return new CommonResponse("Reset password email sent!");
    }

    @RequestMapping(value = "/resetPassData/{token}", method = RequestMethod.POST)
    public CommonResponse resetData(@PathVariable("token") String token, @RequestBody ResetPasswordRequest resetPasswordRequest) throws CbrsException {
        Token resetToken = userService.findTokenByBody(token);
        if (resetToken == null) {
            throw new InvalidTokenException();
        }
        User user = resetToken.getUser();
        if (user == null) {
            throw new UserNotFoundException();
        }

        if (!resetPasswordRequest.getPassword().equals(resetPasswordRequest.getPasswordRetype())) {
            return new CommonResponse("The passwords must match!");
        }
        userService.updatePasswordHash(user, resetPasswordRequest.getPassword());
        return new CommonResponse("Password reset successful!");

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse registerUser(@RequestBody RegistrationRequest registrationRequest) throws CbrsException {
        if (!registrationRequest.getPassword().equals(registrationRequest.getRetypedPassword())) {
            return new CommonResponse("Passwords must be the same!");
        }

        userService.registerUser(registrationRequest.getEmail(), registrationRequest.getPassword());
        return new CommonResponse("Registration submitted! Please read instructions sent to your registration email.");
    }

    @RequestMapping(value = "/resendActToken", method = RequestMethod.POST)
    public CommonResponse resendActToken(@RequestBody ResendTokenRequest resendTokenRequest) {
        userService.resendActivationToken(resendTokenRequest.getEmail());
        return new CommonResponse("Activation link sent! Please follow instructions in the email.");
    }
}
