package com.dataart.citybikerentalservicespring.view.requests;

/**
 * Created by mkrasowski on 27.09.2016.
 */
public class EditStationRequest {
    private int id;
    private String name;
    private String type;
    private String address;
    private String city;
//    private String coordinates;

    public EditStationRequest() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
