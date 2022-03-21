package com.tus.FleetManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FleetInformationDTO {
	

	    private Integer userId;
	    private Integer routeNum;
	    private Integer busNum;

	    private Integer driverId;

	    private String boardingName;

	    private String dropringName;

	    private Date startTime;

	    private Date endTime;

	    

	}


