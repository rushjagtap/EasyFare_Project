package com.tus.farecalculation.entry;

import lombok.Data;

@Data
public class PeakTime {
    private Integer routeid;
    private Integer month;
    private Integer  year;
    private Integer hour;
    private Integer dayofweek;
    private Integer noofpass;

    public PeakTime(Integer routeid, Integer month, Integer year, Integer hour, Integer dayofweek, Integer noofpass) {
        this.routeid = routeid;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.dayofweek = dayofweek;
        this.noofpass = noofpass;
    }
}
