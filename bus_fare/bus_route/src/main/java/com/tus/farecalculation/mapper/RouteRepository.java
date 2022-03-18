package com.tus.farecalculation.mapper;

import com.tus.farecalculation.entry.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RouteRepository  extends JpaRepository<BusRoute,Integer> {
}

