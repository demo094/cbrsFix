package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.constants.BikeType;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.adminexceptions.EntityBindingException;
import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.Slot;
import com.dataart.citybikerentalservicespring.persistence.model.Station;
import com.dataart.citybikerentalservicespring.persistence.repo.BikeRepository;
import com.dataart.citybikerentalservicespring.persistence.repo.SlotRepository;
import com.dataart.citybikerentalservicespring.persistence.repo.StationRepository;
import com.dataart.citybikerentalservicespring.view.TO.BikeTO;
import com.dataart.citybikerentalservicespring.view.TO.StationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mkrasowski on 05.09.2016.
 */
@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private SlotRepository slotRepository;

    @Transactional
    public List<Station> getStationList() {
        return stationRepository.findAll();
    }

    @Transactional
    public Bike getBikeById(int bikeId) {
        return bikeRepository.findOne(bikeId);
    }

    @Transactional
    public void addBike() {
        bikeRepository.save(new Bike());
    }

    @Transactional
    public void deleteBikeById(int bikeId) throws CbrsException {
        if (slotRepository.findSlotByBike(bikeRepository.findOne(bikeId)) != null) {
            throw new EntityBindingException();
        }
        bikeRepository.delete(bikeId);
    }

    @Transactional
    public Station getStationById(Integer stationId) {
        return stationRepository.findById(stationId);
    }

    @Transactional
    public Slot getSlotById(Integer slotId) {
        return slotRepository.findOne(slotId);
    }

    @Transactional
    public Slot getSlotByBike(Bike bike) {
        return slotRepository.findSlotByBike(bike);
    }

    @Transactional
    public List<BikeTO> getBikeTOs() {
        return bikeRepository.findAll().stream().map(BikeTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<StationTO> getStationTOs() {
        return getStationList().stream().map(StationTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateBikeInfo(Integer bikeId, BikeType type, Integer slotId) {
        if(bikeId == null){
            Slot slot = slotRepository.findOne(slotId);
            slot.setBike(null);
        } else {
            Bike bike = bikeRepository.findOne(bikeId);
            bike.setType(type);
            Slot slot = slotRepository.findOne(slotId);
            slot.setBike(bike);
        }
    }

    @Transactional
    public void updateStation(int id, String name, String address, String city, BigDecimal latitude, BigDecimal longitude) {
        Station station = stationRepository.findById(id);
        station.setName(name);
        station.setAddress(address);
        station.setCity(city);
        station.setLatitude(latitude);
        station.setLongitude(longitude);
    }

    @Transactional
    public void addStation(String name, String address, String city, BigDecimal latitude, BigDecimal longitude) {
        stationRepository.save(new Station(name, address, city, latitude, longitude));
    }

    @Transactional
    public void addNewSlotToStation(Station station) {
        slotRepository.save(new Slot(station));
    }

    @Transactional
    public void deleteSlotOnStation(int slotId, Station station) throws CbrsException {
        if (slotRepository.findOne(slotId).getBike() != null) {
            throw new EntityBindingException();
        }
        slotRepository.deleteByIdFromStation(slotId, station);
    }

    @Transactional
    public void deleteStation(Station station) throws CbrsException {
        if (!station.getSlotList().isEmpty()) {
            throw new EntityBindingException();
        }
        stationRepository.delete(station);
    }
}
