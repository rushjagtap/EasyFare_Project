package com.tus.farecalculation.controller;

import com.mall.entry.RouteInformation;
import com.mall.mapper.RouteInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routeInformation")
public class RouteInformationController {

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







}
