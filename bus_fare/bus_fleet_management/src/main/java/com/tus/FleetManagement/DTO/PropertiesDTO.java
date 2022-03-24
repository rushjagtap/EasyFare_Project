package com.tus.FleetManagement.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PropertiesDTO{
    @JsonProperty("marker-color") 
    public String markerColor;
    @JsonProperty("marker-size") 
    public String markerSize;
    @JsonProperty("marker-symbol") 
    public String markerSymbol;
    public String name;
    public Double fare;
}
