package com.tus.FleetManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tus.FleetManagement.Entity.PassengerInfo;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerInfo, Integer>{

	@Query(value = "select count(*) from passenger where routeNumber=?1 and busNumber=?2")
	Integer findByRouteNumberAndBusNumber(String routeNumber,String busNumber);
}
