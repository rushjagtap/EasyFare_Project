package com.tus.EasyFare.DistanceCalculator.VO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary{
    public double lengthInMeters;
    public int travelTimeInSeconds;
    public int trafficDelayInSeconds;
    public int trafficLengthInMeters;
    public Date departureTime;
    public Date arrivalTime;
}
