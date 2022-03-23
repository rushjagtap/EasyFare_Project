package com.tus.farecalculation.controller;

import com.tus.farecalculation.entry.ResponseDTO;
import com.tus.farecalculation.entry.StopPointsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "Distance-tracker-microservice",name = "Distance-tracker-microservice")
public interface Locations {

    @RequestMapping(value = "api/v1/locations",method = RequestMethod.POST)
    public Double getDistance(@RequestBody StopPointsDTO locations);
}
