package com.tus.FleetManagement.DTO;

import lombok.Data;

@Data
public class FeatureDTO{
    public String type;
    public PropertiesDTO properties;
    public GeometryDTO geometry;
}
