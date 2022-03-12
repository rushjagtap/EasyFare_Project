package com.tus.EasyFare.FleetManagement.Bus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.EasyFare.FleetManagement.Bus.Entity.PassengerInfo;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerInfo, Integer>{

}
