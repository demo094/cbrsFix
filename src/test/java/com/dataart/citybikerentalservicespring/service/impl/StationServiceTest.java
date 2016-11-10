package com.dataart.citybikerentalservicespring.service.impl;

import com.dataart.citybikerentalservicespring.persistence.repo.BikeRepository;
import com.dataart.citybikerentalservicespring.persistence.repo.StationRepository;
import com.dataart.citybikerentalservicespring.service.StationService;
import com.dataart.citybikerentalservicespring.view.TO.BikeTO;
import com.dataart.citybikerentalservicespring.view.TO.StationTO;
import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.Slot;
import com.dataart.citybikerentalservicespring.persistence.model.Station;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by mkrasowski on 27.09.2016.
 */
public class StationServiceTest {
    @Mock
    private BikeRepository bikeRepository;
    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationService stationService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getBikeTOs() {
        when(bikeRepository.findAll()).thenReturn(getBikes());
        List<BikeTO> bikeTOs = stationService.getBikeTOs();
        List<Bike> bikes = getBikes();
        assertNotNull(bikeTOs);
        for(int i=0; i<bikes.size(); i++){
            assertEquals(bikes.get(i).getId(), bikeTOs.get(i).getBikeId());
        }
    }

    @Test
    public void getStationTOs() {
        when(stationRepository.findAll()).thenReturn(getStations());
        List<Station> stations = getStations();
        List<StationTO> stationTOs = stationService.getStationTOs();
        assertNotNull(stationTOs);
        assertEquals(Integer.valueOf(1), stationTOs.get(0).getSlotTOs().get(0).getSlotId());
        assertEquals(Integer.valueOf(2), stationTOs.get(0).getSlotTOs().get(0).getBikeId());

        assertEquals("", stationTOs.get(0).getStationType());
        assertEquals(getStations().get(0).getAddress(), stationTOs.get(0).getStationAddress());

        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getCity() != null) {
                assertEquals(stations.get(i).getCity(), stationTOs.get(i).getStationCity());
            }

            assertEquals(new Integer(stations.get(i).getId()), stationTOs.get(i).getStationId());
        }
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        List<Slot> slots = new ArrayList<>();
        Station station5 = new Station(5, "Krakowskie6", "", "KrakowskiePrzedmiescie6", "Lublin", slots);
        Slot slotOnStation5 = new Slot(station5);
        slotOnStation5.setId(1);
        slotOnStation5.setBike(new Bike(2));
        slots.add(slotOnStation5);
        stations.add(station5);
        stations.add(new Station(6, slots));
        stations.add(new Station(7, slots));
        stations.add(new Station(8, slots));
        stations.add(new Station(9, "3Maja17", "ordinary", "3Maja17", "Lublin", slots));

        return stations;
    }

    public List<Bike> getBikes() {
        List<Bike> bikes = new ArrayList<>();
        bikes.add(new Bike(1));
        bikes.add(new Bike(2));
        bikes.add(new Bike(3));
        bikes.add(new Bike(4));
        bikes.add(new Bike(5));
        return bikes;
    }
}
