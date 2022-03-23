package com.tus.FleetManagement.DTO;

import java.util.ArrayList;

import lombok.Data;

@Data
public class RootDTO{
    public String type;
    public ArrayList<FeatureDTO> features;
}
