package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.Slot;
import com.dataart.citybikerentalservicespring.persistence.model.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class StationTO {

    private Integer stationId;
    private String stationName;
    private String stationType;
    private String stationAddress;
    private String stationCity;
//    private String stationCoordinates;
    private List<SlotTO> slotTOs;

    public StationTO() {
    }

    public StationTO(Station station) {
        this.stationId = station.getId();
        this.stationName = station.getName();
        this.stationType = station.getType();
        this.stationAddress = station.getAddress();
        this.stationCity = station.getCity();
//        this.stationCoordinates = null;
        if(station.getSlotList() != null){
            this.slotTOs = returnSlotTOs(station);
        }
    }

    public List<SlotTO> getSlotTOs() {
        return slotTOs;
    }

    public void setSlotTOs(List<SlotTO> slotTOs) {
        this.slotTOs = slotTOs;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    private List<SlotTO> returnSlotTOs(Station station){
        List<SlotTO> slotTOs = new ArrayList<>();
        for (Slot slot : station.getSlotList()){
            slotTOs.add(new SlotTO(slot.getBike(),slot));
        }
        return slotTOs;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationCity() {
        return stationCity;
    }

    public void setStationCity(String stationCity) {
        this.stationCity = stationCity;
    }
//
//    public String getStationCoordinates() {
//        return stationCoordinates;
//    }
//
//    public void setStationCoordinates(String stationCoordinates) {
//        this.stationCoordinates = stationCoordinates;
//    }
}
