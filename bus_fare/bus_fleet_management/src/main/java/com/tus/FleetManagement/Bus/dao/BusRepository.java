package com.tus.EasyFare.FleetManagement.Bus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.EasyFare.FleetManagement.Bus.Entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
	Bus findDistinctByBusNumberAndRouteNumber(long busNumber,String routeNumber);
}
