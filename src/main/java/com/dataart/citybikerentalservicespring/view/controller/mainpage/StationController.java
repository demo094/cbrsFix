package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUser;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.UserNotFoundException;
import com.dataart.citybikerentalservicespring.service.StationService;
import com.dataart.citybikerentalservicespring.service.UserService;
import com.dataart.citybikerentalservicespring.utils.AuthenticationContext;
import com.dataart.citybikerentalservicespring.view.TO.StationTO;
import com.dataart.citybikerentalservicespring.view.TO.UserBikeTO;
import com.dataart.citybikerentalservicespring.view.requests.RentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by mkrasowski on 02.09.2016.
 */

@RestController
public class StationController {

    @Autowired
    private StationService stationService;
    @Autowired
    private UserService userService;


    @GetMapping("/api/stations")
    @PreAuthorize("hasRole('USER')")
    public List<StationTO> stationTOs() {
        return stationService.getStationTOs();
    }

    @GetMapping("/api/station/{id}")
    public StationTO stationTOById(@PathVariable("id") Integer stationId) {
        return new StationTO(stationService.getStationById(stationId));
    }

    @GetMapping("/api/userbike")
    @PreAuthorize("hasRole('USER')")
    public UserBikeTO userBikeTO() throws CbrsException {
        AuthenticatedUser authenticatedUser = AuthenticationContext.getAuthenticatedUser();
            return new UserBikeTO(userService.findById(authenticatedUser.getId()));
    }

}
