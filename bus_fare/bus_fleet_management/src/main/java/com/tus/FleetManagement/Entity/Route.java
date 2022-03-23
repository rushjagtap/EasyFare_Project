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
@Table(name = "route_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	@Column(name = "id")
	private Integer routeid;
	@Column(name="route_number")
	private String routeNum;
	@Column(name="bus_number")
	private String busNum;

}
