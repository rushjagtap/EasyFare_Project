package com.mall.mapper;

import com.mall.entry.BusRoute;
import com.mall.entry.RouteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteHistoryRepository extends JpaRepository<RouteHistory,Integer> {
}

