package com.tus.farecalculation.entry;

import lombok.Data;

@Data
public class PeakTime {
    private Integer routeId;
    private Integer month;
    private Integer  year;
    private Integer hour;
    private Integer dateofweek;
    private Integer noofpass;
}
