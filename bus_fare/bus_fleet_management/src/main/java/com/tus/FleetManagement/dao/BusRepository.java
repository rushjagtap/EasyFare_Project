package com.tus.FleetManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.FleetManagement.Entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
	Bus findDistinctByBusNumberAndRouteNumber(long busNumber,String routeNumber);
}
