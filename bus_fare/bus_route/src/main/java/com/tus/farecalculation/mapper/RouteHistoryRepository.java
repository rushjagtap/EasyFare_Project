package com.tus.farecalculation.mapper;

import com.tus.farecalculation.entry.RouteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteHistoryRepository extends JpaRepository<RouteHistory,Integer> {
}

