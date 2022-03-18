package com.tus.EasyFare.FleetManagement.DTO;

public class PassengerDTO {
	private Integer userid;
	private String startpoint;
	private String routeNumber;
	private long busNumber;
	private Integer availableBalance;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getStartpoint() {
		return startpoint;
	}
	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}
	public String getRouteNumber() {
		return routeNumber;
	}
	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}
	public long getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(long busNumber) {
		this.busNumber = busNumber;
	}
	public Integer getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(Integer availableBalance) {
		this.availableBalance = availableBalance;
	}
	
}
