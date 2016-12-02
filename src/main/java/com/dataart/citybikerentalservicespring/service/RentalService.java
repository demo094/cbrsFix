package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.bikeexceptions.BikeAlreadyBoundException;
import com.dataart.citybikerentalservicespring.exceptions.bikeexceptions.BikeNotFoundException;
import com.dataart.citybikerentalservicespring.exceptions.bikeexceptions.NoBikeBoundException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.UserBalanceException;
import com.dataart.citybikerentalservicespring.persistence.model.*;
import com.dataart.citybikerentalservicespring.persistence.repo.*;
import com.dataart.citybikerentalservicespring.utils.CalculationUtil;
import com.dataart.citybikerentalservicespring.view.responses.PriceIntervalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 * Created by mkrasowski on 06.09.2016.
 */
@Service
public class RentalService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private RentalHistoryRepository rentalHistoryRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private PriceIntervalService priceIntervalService;

    @Transactional
    public void rentBikeFromSlot(User user, Integer slotId) throws CbrsException {
        Slot slot = slotRepository.findOne(slotId);
        Bike bike = bikeRepository.findBikeBySlotId(slot.getId());
        Bike rentedBike = userRepository.findRentedBike(user.getId());
        user = userRepository.findOne(user.getId());
        if (user.getBalance() == null || user.getBalance().intValue() <= 0) {
            throw new UserBalanceException();
        } else if (bike == null) {
            throw new BikeNotFoundException();
        } else if (rentedBike != null) {
            throw new BikeAlreadyBoundException();
        } else {
            user.setBike(bike);
            userRepository.save(user);
            rentalHistoryRepository.save(new RentalHistory(bike, user, slot));
            slot.setBike(null);
            slotRepository.save(slot);
        }
    }

    @Transactional
    public void returnBike(User user, Integer slotId) throws CbrsException {
        Bike rentedBike = userRepository.findRentedBike(user.getId());
        Slot slot = slotRepository.findOne(slotId);
        if (rentedBike == null) {
            throw new NoBikeBoundException();
        } else {
            user.setBike(null);
            userRepository.save(user);
            slot.setBike(rentedBike);
            slotRepository.save(slot);
            rentalHistoryRepository.updateLastRecord(slot.getId(), Instant.now(), rentedBike.getId(), user.getId());
            substractMoneyFromUser(user, rentedBike);
        }
    }

    private void substractMoneyFromUser(User user, Bike bike) {
        RentalHistory rentalHistory = rentalHistoryRepository.findFirstByBikeAndUserOrderByIdDesc(bike, user);
        List<PriceIntervalResponse> priceIntervalList = priceIntervalService.getPriceIntervalTOs();
        Duration tripDuration = Duration.between(rentalHistory.getBeginTime(), rentalHistory.getEndTime());
        long secondsOfRent = tripDuration.toMillis() / 1000;
        BigDecimal tripPrice = CalculationUtil.calculateTripPrice(secondsOfRent, priceIntervalList);
        paymentRepository.save(new Payment(user, new Date(), bike, tripPrice));
        userRepository.substractMoney(tripPrice, user.getId());
    }

    @Transactional
    public RentalHistory getLastRental(User user) {
        return rentalHistoryRepository.findFirstByUserOrderByIdDesc(user);
    }

    @Transactional
    public Payment getLastTripPrice(User user) {
        return paymentRepository.findFirstByUserOrderByIdDesc(user);
    }
}
