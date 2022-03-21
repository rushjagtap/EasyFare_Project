package com.tus.farecalculation.controller;

import com.tus.farecalculation.entry.FareDTO;
import com.tus.farecalculation.entry.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "user-management-service",name = "user-management-service")
public interface Deduct {
    @RequestMapping(value = "api/v1/user/{userid}/deduct", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDTO> deductFareFromCard(@PathVariable Integer userid, @RequestBody FareDTO fareDTO);
}
