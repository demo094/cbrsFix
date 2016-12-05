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
import com.dataart.citybikerentalservicespring.view.requests.PaymentRequest;
import com.dataart.citybikerentalservicespring.view.requests.RegistrationRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResendTokenRequest;
import com.dataart.citybikerentalservicespring.view.requests.ResetPasswordRequest;
import com.dataart.citybikerentalservicespring.view.responses.CommonResponse;
import com.dataart.citybikerentalservicespring.view.responses.PaymentResponse;
import com.dataart.citybikerentalservicespring.view.responses.RentalStatusResponse;
import com.dataart.citybikerentalservicespring.view.responses.UserPanelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

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
        List<GrantedAuthority> authorities = authUser.getAuthorities();
        User user = userService.findById(authUser.getId());
        RentalHistory rentalHistory = rentalService.getLastRental(user);
        Payment payment = rentalService.getLastTripPrice(user);
        return new UserPanelResponse(user, rentalHistory, payment, authorities);
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

    @RequestMapping(value = "/rent/status", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public RentalStatusResponse rentStatus() {
        AuthenticatedUser authenticatedUser = AuthenticationContext.getAuthenticatedUser();
        User user = userService.findById(authenticatedUser.getId());
        RentalHistory rentalHistory = rentalService.getLastRental(user);

        return new RentalStatusResponse(user, rentalHistory);
    }
}
