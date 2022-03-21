package com.tus.farecalculation.vo;

import lombok.Data;

import java.util.Date;

@Data
public class FleetInformation {
    private Integer userId;
    private String routeNum;
    private String busNum;

    private Integer driverId;

    private String boardingName;

    private String dropringName;

    private Date startTime;

    private Date endTime;

    

}
