package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.Slot;

/**
 * Created by mkrasowski on 23.09.2016.
 */
public class SlotTO {
    private Integer slotId;
    private Integer bikeId;

    public SlotTO(Bike bike, Slot slot) {
        if (slot == null) {
            this.slotId = null;
        } else {
            this.slotId = slot.getId();
        }
        if (bike == null) {
            this.bikeId = null;
        } else {
            this.bikeId = bike.getId();
        }
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }
}
