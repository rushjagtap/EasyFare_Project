package com.tus.FleetManagement.DTO;

import java.util.ArrayList;

import lombok.Data;

@Data
public class GeometryDTO{
	
    public String type;
    public ArrayList<Double> coordinates;
}
