package com.mall.mapper;

import com.mall.entry.StopInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface StopInformationRepository extends JpaRepository<StopInformation,Integer> {
    @Query("select c from stop_information c where c.routeInformationId=?1 and c.stopName=?2")
    List<StopInformation> findAll(Integer routeInformationId, String dropringName);

    @Query("select sum(c.price) from stop_information c where c.id<?1  and  c.routeInformationId=?2")
    BigDecimal findAll(Integer stopInformationId,Integer routeInformationId);
}

