package com.tus.FleetManagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.FleetManagement.Entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
	Optional<Bus> findDistinctByBusNumberAndRouteNumber(String busNumber,String routeNumber);
	
}
