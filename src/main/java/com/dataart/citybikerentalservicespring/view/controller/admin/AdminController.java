package com.dataart.citybikerentalservicespring.view.controller.admin;

import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.adminexceptions.PriceIntervalNotFoundException;
import com.dataart.citybikerentalservicespring.exceptions.adminexceptions.StationNotFoundException;
import com.dataart.citybikerentalservicespring.exceptions.bikeexceptions.BikeNotFoundException;
import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import com.dataart.citybikerentalservicespring.persistence.model.Slot;
import com.dataart.citybikerentalservicespring.persistence.model.Station;
import com.dataart.citybikerentalservicespring.service.PriceIntervalService;
import com.dataart.citybikerentalservicespring.service.StationService;
import com.dataart.citybikerentalservicespring.service.UserService;
import com.dataart.citybikerentalservicespring.view.TO.*;
import com.dataart.citybikerentalservicespring.view.requests.EditBikeRequest;
import com.dataart.citybikerentalservicespring.view.requests.EditStationRequest;
import com.dataart.citybikerentalservicespring.view.requests.ManipulatePriceIntervalRequest;
import com.dataart.citybikerentalservicespring.view.responses.CommonResponse;
import com.dataart.citybikerentalservicespring.view.responses.EditBikeResponse;
import com.dataart.citybikerentalservicespring.view.responses.PriceIntervalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mkrasowski on 21.10.2016.
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private StationService stationService;
    @Autowired
    private PriceIntervalService priceIntervalService;

    @RequestMapping(value = "/api/adminbikelist", method = RequestMethod.GET)
    public List<BikeTO> bikeManagementData() {
        return stationService.getBikeTOs();
    }

    @RequestMapping(value = "/api/adminstationlist", method = RequestMethod.GET)
    public List<StationTO> stationManagementData() {
        return stationService.getStationTOs();
    }

    @RequestMapping(value = "/api/adminpriceintervals", method = RequestMethod.GET)
    public List<PriceIntervalResponse> priceIntervalManagementData() {
        return priceIntervalService.getPriceIntervalTOs();
    }

    @RequestMapping(value = "/api/bike/{bikeId}", method = RequestMethod.GET)
    public EditBikeResponse editBikeData(@PathVariable("bikeId") int bikeId) throws CbrsException {
        Bike bike = stationService.getBikeById(bikeId);
        if (bike == null) {
            throw new BikeNotFoundException();
        }
        Slot slot = stationService.getSlotByBike(bike);
        SlotTO slotTO = new SlotTO(bike, slot);
        List<StationTO> stationTOs = stationService.getStationTOs();
        return new EditBikeResponse(slotTO, stationTOs);
    }

    @RequestMapping(value = "/api/adminstation/{stationId}", method = RequestMethod.GET)
    public StationTO editStationData(@PathVariable("stationId") int stationId) throws CbrsException {
        Station station = stationService.getStationById(stationId);
        if (station == null) {
            throw new StationNotFoundException();
        }
        return new StationTO(station);
    }

    @RequestMapping(value = "/api/editpriceinterval/{priceIntervalId}", method = RequestMethod.GET)
    public PriceIntervalResponse editPriceIntervalData(@PathVariable("priceIntervalId") int priceIntervalId) throws CbrsException {
        PriceInterval priceInterval = priceIntervalService.getPriceIntervalById(priceIntervalId);
        if (priceInterval == null) {
            throw new PriceIntervalNotFoundException();
        }
        return new PriceIntervalResponse(priceInterval);
    }

    @RequestMapping(value = "/api/station", method = RequestMethod.PUT)
    public CommonResponse addStation(@RequestBody EditStationRequest addStationRequest) {
        stationService.addStation(addStationRequest.getName(), addStationRequest.getType(), addStationRequest.getAddress(),
                addStationRequest.getCity());
        return new CommonResponse("Station added");
    }

    @RequestMapping(value = "/api/slot/{stationId}", method = RequestMethod.PUT)
    public CommonResponse addSlot(@PathVariable("stationId") int stationId) {
        stationService.addNewSlotToStation(stationService.getStationById(stationId));
        return new CommonResponse("Slot added");
    }

    @RequestMapping(value = "/api/station", method = RequestMethod.POST)
    public CommonResponse updateStation(@RequestBody EditStationRequest editStationRequest) {
        stationService.updateStation(editStationRequest.getId(), editStationRequest.getName(),
                editStationRequest.getType(), editStationRequest.getAddress(), editStationRequest.getCity());
        return new CommonResponse("Station edited");
    }

    @RequestMapping(value = "/api/station/{stationId}", method = RequestMethod.DELETE)
    public CommonResponse deleteStation(@PathVariable("stationId") int stationId) throws CbrsException {
        stationService.deleteStation(stationService.getStationById(stationId));
        return new CommonResponse("Station deleted");
    }

    @RequestMapping(value = "/api/bike/{bikeId}", method = RequestMethod.DELETE)
    public CommonResponse deleteBike(@PathVariable("bikeId") int bikeId) throws CbrsException {
        stationService.deleteBikeById(bikeId);
        return new CommonResponse("Bike deleted!");
    }

    @RequestMapping(value = "/api/bike", method = RequestMethod.PUT)
    public CommonResponse addBike() {
        stationService.addBike();
        return new CommonResponse("Bike added!");
    }

    @RequestMapping(value = "/api/bike", method = RequestMethod.POST)
    public CommonResponse updateBike(@RequestBody EditBikeRequest editBikeRequest) {
        stationService.updateBikeInfo(editBikeRequest.getId(), editBikeRequest.getType(),
                editBikeRequest.getSlotId());

        return new CommonResponse("Bike updated!");
    }

    @RequestMapping(value = "/api/priceInterval", method = RequestMethod.POST)
    public CommonResponse updatePriceInterval(@RequestBody ManipulatePriceIntervalRequest manipulatePriceIntervalRequest) {
        priceIntervalService.updatePriceInterval(manipulatePriceIntervalRequest.getId(),
                manipulatePriceIntervalRequest.getEnd(), manipulatePriceIntervalRequest.getPrice());
        return new CommonResponse("Price interval updated");
    }

    @RequestMapping(value = "/api/priceinterval/{priceIntervalId}", method = RequestMethod.DELETE)
    public CommonResponse deletePriceInterval(@PathVariable("priceIntervalId") int priceIntervalId) {
        priceIntervalService.deletePriceIntervalById(priceIntervalId);
        return new CommonResponse("Price interval deleted");
    }

    @RequestMapping(value = "/api/priceinterval", method = RequestMethod.PUT)
    public CommonResponse addPriceInterval(@RequestBody ManipulatePriceIntervalRequest manipulatePriceIntervalRequest) {
        priceIntervalService.addPriceInterval(manipulatePriceIntervalRequest.getEnd(),
                manipulatePriceIntervalRequest.getPrice());

        return new CommonResponse("Price interval added");
    }
}
