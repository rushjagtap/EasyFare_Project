package com.tus.usermanagement.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO{
	private Integer routeid;
	private Integer month;
	private Integer year;
	private Integer hour;
	private Integer dayofweek;
	private Integer noofpass;
}
