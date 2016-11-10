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
import com.dataart.citybikerentalservicespring.view.TO.AuthenticationTO;
import com.dataart.citybikerentalservicespring.view.TO.PaymentTO;
import com.dataart.citybikerentalservicespring.view.TO.UserPanelTO;
import com.dataart.citybikerentalservicespring.view.requests.PaymentRequest;
import com.dataart.citybikerentalservicespring.view.requests.RegistrationRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResendTokenRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResetPasswordRequest;
import com.dataart.citybikerentalservicespring.view.responses.CommonResponse;
import com.dataart.citybikerentalservicespring.view.responses.ErrorResponse;
import com.dataart.citybikerentalservicespring.view.responses.RentalSynchroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created by mkrasowski on 20.10.2016.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/api/userpanel", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public UserPanelTO userPanelTO() throws CbrsException {
        AuthenticatedUser authUser = AuthenticationContext.getAuthenticatedUser();
            User user = userService.findById(authUser.getId());
            RentalHistory rentalHistory = rentalService.getLastRental(user);
            Payment payment = rentalService.getLastTripPrice(user);
            return new UserPanelTO(user, rentalHistory, payment);
    }

    @RequestMapping(value = "/resetPassEmail", method = RequestMethod.POST)
    public CommonResponse angularResetPassword(@RequestBody ResendTokenRequest resendTokenRequest) {
        userService.sendResetPasswordEmail(resendTokenRequest.getEmail());
        return new CommonResponse("Email sent!");
    }

    @RequestMapping(value = "/resetPassData", method = RequestMethod.POST)
    public CommonResponse angularResetData(@RequestBody ResetPasswordRequest resetPasswordRequest) throws CbrsException {
        if (!resetPasswordRequest.getPassword().equals(resetPasswordRequest.getPasswordRetype())) {
            return new CommonResponse("The passwords must match!");
        }
        User user = userService.findByEmail(resetPasswordRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        userService.updatePasswordHash(user, resetPasswordRequest.getPassword());
        return new CommonResponse("Password reset successful!");

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse angularRegisterUser(@RequestBody RegistrationRequest registrationRequest) throws CbrsException {
        if (!registrationRequest.getPassword().equals(registrationRequest.getRetypedPassword())) {
            return new CommonResponse("Passwords must be the same!");
        }

        userService.registerUser(registrationRequest.getEmail(), registrationRequest.getPassword());
        return new CommonResponse("Registration submitted! Please read instructions sent to your registration email.");
    }

    @RequestMapping(value = "/resendActToken", method = RequestMethod.POST)
    public CommonResponse angularResendActToken(@RequestBody ResendTokenRequest resendTokenRequest) {
        userService.resendActivationToken(resendTokenRequest.getEmail());
        return new CommonResponse("Activation link sent! Please follow instructions in the email.");
    }

    @RequestMapping(value = "/api/payment", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public PaymentTO angularPaymentData() {
        return new PaymentTO(AuthenticationContext.getAuthenticatedUser());
    }

    @RequestMapping(value = "/api/paymentData", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public CommonResponse angularPaymentSubmit(@RequestBody PaymentRequest paymentRequest) throws CbrsException {
        userService.updateBalance(paymentRequest.getIdUser(), paymentRequest.getAmount());
        return new CommonResponse("Payment done!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse userAuthentication(@RequestBody AuthenticationTO authenticationTO, HttpServletResponse response) {
        response.addCookie(new Cookie("accessToken" ,loginService.createAuthenticationToken(authenticationTO)));
        return new CommonResponse("Login ok!");
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public CommonResponse confirmationPage(@RequestParam String token) throws CbrsException {
        Token storedToken = userService.findTokenByBody(token);
        if (storedToken == null) {
            throw new InvalidTokenException();
        }
        User user = storedToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (storedToken.getExpirationDate().getTime() - calendar.getTime().getTime() <= 0) {
            return new CommonResponse("Account not activated!");
        } else {
            userService.setUserActivated(user, true);
            return new CommonResponse("Account activated");
        }
    }

    @RequestMapping(value = "/api/rentalBeginTime", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public RentalSynchroResponse synchronizeTimer(){
        AuthenticatedUser authenticatedUser = AuthenticationContext.getAuthenticatedUser();
        User user = userService.findById(authenticatedUser.getId());
        RentalHistory rentalHistory = rentalService.getLastRental(user);

        return new RentalSynchroResponse(user, rentalHistory);
    }
}
