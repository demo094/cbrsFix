package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.Station;
import com.vividsolutions.jts.geom.Point;
import org.geolatte.geom.Geometry;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by mkrasowski on 22.09.2016.
 */
public class StationTO {

    private Integer stationId;
    private String stationName;
    private String stationType;
    private String stationAddress;
    private String stationCity;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private List<SlotTO> slotTOs;

    public StationTO() {
    }

    public StationTO(Station station) {
        this.stationId = station.getId();
        this.stationName = station.getName();
        this.stationType = station.getType();
        this.stationAddress = station.getAddress();
        this.stationCity = station.getCity();
        this.latitude = station.getLatitude();
        this.longitude = station.getLongitude();
        if (station.getSlotList() != null) {
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

    private List<SlotTO> returnSlotTOs(Station station) {
        return station.getSlotList().stream().map(slot -> new SlotTO(slot.getBike(), slot)).collect(Collectors.toList());
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
