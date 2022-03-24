package com.tus.FleetManagement.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stop_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopInfo {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	@Column(name = "id")
	private Integer stopId;
	
	@Column(name = "route_information_id")
	private Integer routeInfoId;
	
	@Column(name = "stop_number")
	private Integer stopNumber;
	
	 @Column(name = "stop_name")
	private String stopName;
	 
	 @Column(name = "latitude") 
	private Double latitude;
	 
	 @Column(name = "longitude")
	private Double longitude;
	 
	 @Column(name = "distance")
	private Double distance;
	 
	 @Column(name = "price")
	private Double price;
	
}
