package com.dataart.citybikerentalservicespring.constants;

/**
 * Created by mkrasowski on 02.12.2016.
 */
public enum BikeType {
    ORDINARY("ordinary"),
    CHILDREN("children"),
    MOUNTAIN("mountain");

    private String typeValue;

    BikeType(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public static BikeType getBikeType(String type){
        for (BikeType enumType : BikeType.values()){
            if(type.equals(enumType.typeValue)){
                return enumType;
            }
        }
        return null;
    }
}
