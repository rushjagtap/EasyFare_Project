package com.tus.FleetManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.FleetManagement.Entity.StopInfo;

@Repository
public interface StopInfoRepository extends JpaRepository<StopInfo, Integer> {

}
