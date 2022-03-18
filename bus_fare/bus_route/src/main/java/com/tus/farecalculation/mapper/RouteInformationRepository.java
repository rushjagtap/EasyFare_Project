package com.tus.farecalculation.mapper;

import com.tus.farecalculation.entry.RouteInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteInformationRepository extends JpaRepository<RouteInformation,Integer> {
    @Query("select c from route_information c where c.routeNumber=?1 and c.busNumber=?2")
    List<RouteInformation> findAll(Integer routeNum, Integer busNum);
}

