package com.tus.farecalculation.controller;

import com.tus.farecalculation.entry.FareDTO;
import com.tus.farecalculation.entry.ResponseDTO;
import com.tus.farecalculation.entry.RouteInformation;
import com.tus.farecalculation.mapper.RouteInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routeInformation")
public class RouteInformationController   {

    @Autowired
    private Deduct deductService;

    @Autowired
    RouteInformationRepository routeInformationRepository;



    @GetMapping("/getAll")
    public List<RouteInformation> queryStudent(){
        List<RouteInformation> list ;
        list = routeInformationRepository.findAll();
        return list  ;
    }


    @PostMapping(value="/add", produces = {"application/json;charset=UTF-8"})
    public RouteInformation add(@RequestBody RouteInformation routeInformation){
        routeInformation= routeInformationRepository.save(routeInformation);
        return routeInformation;
    }



    @RequestMapping(value = "user/{userid}/deduct", method = RequestMethod.PUT)
    public String deductFareFromCard(@PathVariable Integer userid, @RequestBody FareDTO fareDTO){
        String messgae = deductService.deductFareFromCard(userid, fareDTO).getBody().getMessgae();
        return messgae;
    }



}
