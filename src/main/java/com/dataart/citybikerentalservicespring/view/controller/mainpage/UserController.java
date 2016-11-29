package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUser;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.InvalidTokenException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.UserNotFoundException;
import com.dataart.citybikerentalservicespring.persistence.model.Payment;
import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.Token;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.service.LoginService;
import com.dataart.citybikerentalservicespring.service.RentalService;
import com.dataart.citybikerentalservicespring.service.UserService;
import com.dataart.citybikerentalservicespring.utils.AuthenticationContext;
import com.dataart.citybikerentalservicespring.view.requests.AuthenticationRequest;
import com.dataart.citybikerentalservicespring.view.responses.PaymentResponse;
import com.dataart.citybikerentalservicespring.view.responses.UserPanelResponse;
import com.dataart.citybikerentalservicespring.view.requests.PaymentRequest;
import com.dataart.citybikerentalservicespring.view.requests.RegistrationRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResendTokenRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResetPasswordRequest;
import com.dataart.citybikerentalservicespring.view.responses.CommonResponse;
import com.dataart.citybikerentalservicespring.view.responses.RentalStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;

/**
 * Created by mkrasowski on 20.10.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/userpanel", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public UserPanelResponse userPanelTO() throws CbrsException {
        AuthenticatedUser authUser = AuthenticationContext.getAuthenticatedUser();
        User user = userService.findById(authUser.getId());
        RentalHistory rentalHistory = rentalService.getLastRental(user);
        Payment payment = rentalService.getLastTripPrice(user);
        return new UserPanelResponse(user, rentalHistory, payment);
    }

    @RequestMapping(value = "/resetPassEmail", method = RequestMethod.POST)
    public CommonResponse resetPassword(@RequestBody ResendTokenRequest resendTokenRequest) {
        userService.sendResetPasswordEmail(resendTokenRequest.getEmail());
        return new CommonResponse("Email sent!");
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

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public PaymentResponse paymentData() {
        return new PaymentResponse(AuthenticationContext.getAuthenticatedUser());
    }

    @RequestMapping(value = "/paymentData", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public CommonResponse payment(@Valid @RequestBody PaymentRequest paymentRequest) throws CbrsException {
        userService.updateBalance(paymentRequest.getIdUser(), paymentRequest.getAmount());
        return new CommonResponse("Payment done!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse userAuthentication(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        response.addCookie(new Cookie("accessToken", loginService.createAuthenticationToken(authenticationRequest)));
        return new CommonResponse("Login ok!");
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

    @RequestMapping(value = "/rent/status", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public RentalStatusResponse rentStatus() {
        AuthenticatedUser authenticatedUser = AuthenticationContext.getAuthenticatedUser();
        User user = userService.findById(authenticatedUser.getId());
        RentalHistory rentalHistory = rentalService.getLastRental(user);

        return new RentalStatusResponse(user, rentalHistory);
    }
}
