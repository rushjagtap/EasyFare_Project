package com.tus.FleetManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.FleetManagement.Entity.PassengerInfo;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerInfo, Integer>{

}
