package com.dataart.citybikerentalservicespring.view.requests;

/**
 * Created by mkrasowski on 23.09.2016.
 */
public class EditBikeRequest {
    private Integer bikeId;
    private String type;
    private Integer slotId;
    private Integer oldSlotId;

    public Integer getId() {
        return bikeId;
    }

    public void setId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getOldSlotId() {
        return oldSlotId;
    }

    public void setOldSlotId(Integer oldSlotId) {
        this.oldSlotId = oldSlotId;
    }
}
