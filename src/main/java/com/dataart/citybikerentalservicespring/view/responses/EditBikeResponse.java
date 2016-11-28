package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.view.TO.SlotTO;
import com.dataart.citybikerentalservicespring.view.TO.StationTO;

import java.util.List;

/**
 * Created by mkrasowski on 26.10.2016.
 */
public class EditBikeResponse {
    private SlotTO slotTO;
    private List<StationTO> stationTOs;

    public EditBikeResponse(SlotTO slotTO, List<StationTO> stationTOs) {
        this.slotTO = slotTO;
        this.stationTOs = stationTOs;
    }

    public EditBikeResponse() {
    }

    public SlotTO getSlotTO() {
        return slotTO;
    }

    public void setSlotTO(SlotTO slotTO) {
        this.slotTO = slotTO;
    }

    public List<StationTO> getStationTOs() {
        return stationTOs;
    }

    public void setStationTOs(List<StationTO> stationTOs) {
        this.stationTOs = stationTOs;
    }
}
