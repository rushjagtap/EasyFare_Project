package com.tus.farecalculation.mapper;

import com.tus.farecalculation.entry.BusRoute;
import com.tus.farecalculation.entry.RouteHistory;
import com.tus.farecalculation.entry.StopInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RouteHistoryRepository extends JpaRepository<RouteHistory,Integer> {


    @Query("select c from user_travel_history c where c.userId=?1 and c.tripStartTime=?2 ")
    List<RouteHistory> findAll(Integer userId, Date  startTime);
}

