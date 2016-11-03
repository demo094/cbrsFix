package com.dataart.citybikerentalservicespring.view.TO;

import java.util.List;

/**
 * Created by mkrasowski on 26.10.2016.
 */
public class EditBikeTO {
    private SlotTO slotTO;
    private List<StationTO> stationTOs;

    public EditBikeTO(SlotTO slotTO, List<StationTO> stationTOs) {
        this.slotTO = slotTO;
        this.stationTOs = stationTOs;
    }

    public EditBikeTO() {
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
