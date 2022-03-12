package com.tus.EasyFare.DistanceCalculator.VO;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leg{
    public Summary summary;
    public ArrayList<Point> points;
}