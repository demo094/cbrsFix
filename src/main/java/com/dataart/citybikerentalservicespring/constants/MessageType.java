
package com.dataart.citybikerentalservicespring.constants;

/**
 * Simple enum class which passes a type of the message TO the user.
 * 
 * @author mkrasowski
 */
public enum MessageType {
    ERROR("Error: "), MESSAGE("Info: ");
    private final String text;
    
    MessageType(String text){
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    
}
