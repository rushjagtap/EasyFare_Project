package com.tus.farecalculation.controller;

import com.tus.farecalculation.entry.BusRoute;
import com.tus.farecalculation.mapper.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @RequestMapping("/getAll")
    public List<BusRoute> queryStudent(){
        List<BusRoute> list ;
        list = routeRepository.findAll();
        return list  ;
    }
    @RequestMapping("/getOne")
    public Optional<BusRoute> queryStudent(@RequestParam Integer id){
        Optional<BusRoute> busroute = routeRepository.findById(id);
        return busroute;
    }
    @RequestMapping(value="/add", produces = {"application/json;charset=UTF-8"})
    public BusRoute addStudent(@RequestBody BusRoute route){
        route= routeRepository.save(route);
        return route;
    }

    @RequestMapping(value="/delete")
    public String delete(@RequestParam Integer id){
        routeRepository.deleteById(id);
        return "delete success";
    }

}
