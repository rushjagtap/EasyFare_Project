package com.tus.FleetManagement.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passenger_info_details")
public class PassengerInfo {

	@Id
	@Column(name = "user_id")
	private Integer userid;
	@Column(name="start_time")
	private LocalDateTime startTime;
	@Column(name = "start_point")
	private String start_point;
	@Column(name = "end_point")
	private String end_point;
	public String getEnd_point() {
		return end_point;
	}
	public void setEnd_point(String end_point) {
		this.end_point = end_point;
	}
	@Column(name="route_number")
	private String routeNumber;
	@Column(name="bus_number")
	private String busNumber;
	@Column(name="driver_id")
	private String driverId;
	@Column(name="avail_balance")
	private Integer availableBalance;
	
	public PassengerInfo() {
		// TODO Auto-generated constructor stub
	}
	public PassengerInfo(Integer userid, LocalDateTime startTime, String start_point, String end_point, String routeNumber,
			String busNumber, String driverId, Integer availableBalance) {
		super();
		this.userid = userid;
		this.startTime = startTime;
		this.start_point = start_point;
		this.end_point = end_point;
		this.routeNumber = routeNumber;
		this.busNumber = busNumber;
		this.driverId = driverId;
		this.availableBalance = availableBalance;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public String getStart_point() {
		return start_point;
	}
	public void setStart_point(String start_point) {
		this.start_point = start_point;
	}
	public String getRouteNumber() {
		return routeNumber;
	}
	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public Integer getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(Integer availableBalance) {
		this.availableBalance = availableBalance;
	}
	
	
}
