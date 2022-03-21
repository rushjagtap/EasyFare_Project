package com.tus.FleetManagement.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;



@Entity
@Component
public class Bus implements Serializable {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	private long Id;
	
	@Column
	private String busNumber;
	
	@Column
	private String busStatus;
	
	@Column
	private String driverId;
	
	
	@Column
	private String city;
	
	@Column
	private String startStation;
	
	@Column
	private String endPoint;
	
	@Column
	private String routeNumber;
	
	
	public Bus() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Bus(long busId, String busNumber, String busStatus, String driverId, String city, String startStation,
			String endPoint, String routeNumber) {
		super();
		this.Id = busId;
		this.busNumber = busNumber;
		this.busStatus = busStatus;
		this.driverId = driverId;
		this.city = city;
		this.startStation = startStation;
		this.endPoint = endPoint;
		this.routeNumber = routeNumber;
	}



	/**
	 * @return the busId
	 */
	public long getBusId() {
		return Id;
	}

	/**
	 * @param busId the busId to set
	 */
	public void setBusId(long busId) {
		this.Id = busId;
	}

	/**
	 * @return the busNumber
	 */
	public String getBusNumber() {
		return busNumber;
	}

	/**
	 * @param busNumber the busNumber to set
	 */
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	/**
	 * @return the busStatus
	 */
	public String getBusStatus() {
		return busStatus;
	}

	/**
	 * @param busStatus the busStatus to set
	 */
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}

	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the startStation
	 */
	public String getStartStation() {
		return startStation;
	}

	/**
	 * @param startStation the startStation to set
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the routeNumber
	 */
	public String getRouteNumber() {
		return routeNumber;
	}

	/**
	 * @param routeNumber the routeNumber to set
	 */
	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}



}
