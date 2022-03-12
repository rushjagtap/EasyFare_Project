package com.mall.mapper;

import com.mall.entry.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RouteRepository  extends JpaRepository<BusRoute,Integer> {
}

