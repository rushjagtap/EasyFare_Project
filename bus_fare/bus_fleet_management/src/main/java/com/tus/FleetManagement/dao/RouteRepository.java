package com.tus.FleetManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.FleetManagement.Entity.Route;
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

	List<Route> findByRouteNumAndBusNum(String routeNum,String busNum);
}
