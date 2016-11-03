package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUser;
import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.UserNotFoundException;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.service.RentalService;
import com.dataart.citybikerentalservicespring.service.UserService;
import com.dataart.citybikerentalservicespring.utils.AuthenticationContext;
import com.dataart.citybikerentalservicespring.utils.WebUtil;
import com.dataart.citybikerentalservicespring.view.requests.RentalRequest;
import com.dataart.citybikerentalservicespring.view.responses.RentalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mkrasowski on 06.09.2016.
 */
@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/station/slot/{id}/rent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public RentalResponse angularRentBike(@PathVariable("id") Integer slotId) throws CbrsException {
        AuthenticatedUser authenticatedUser = AuthenticationContext.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new CbrsException();
        } else {
            User user = userService.findById(authenticatedUser.getId());
            rentalService.rentBikeFromSlot(user, slotId);
            return new RentalResponse("Bike rented!");
        }
    }

    @RequestMapping(value = "/api/station/slot/{id}/return", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public RentalResponse angularReturnBike(@PathVariable("id") Integer slotId) throws CbrsException {
        AuthenticatedUser authenticatedUser = AuthenticationContext.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new CbrsException();
        } else {
            User user = userService.findById(authenticatedUser.getId());
            rentalService.returnBike(user, slotId);
            return new RentalResponse("Bike returned!");
        }
    }

}
