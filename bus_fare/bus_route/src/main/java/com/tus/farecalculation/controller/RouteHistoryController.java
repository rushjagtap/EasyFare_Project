package com.tus.farecalculation.controller;

import com.tus.farecalculation.entry.BusRoute;
import com.tus.farecalculation.entry.RouteHistory;
import com.tus.farecalculation.mapper.RouteHistoryRepository;
import com.tus.farecalculation.mapper.RouteRepository;
import io.swagger.annotations.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routeHistory")
public class RouteHistoryController {

    @Autowired
    RouteHistoryRepository routeHistoryRepository;

    @RequestMapping("/getAll")
    public List<RouteHistory> queryStudent(){
        List<RouteHistory> list ;
        list = routeHistoryRepository.findAll();
        return list  ;
    }
    @RequestMapping("/getOne")
    public Optional<RouteHistory> queryStudent(@RequestParam Integer id){
        Optional<RouteHistory> busroute = routeHistoryRepository.findById(id);
        return busroute;
    }
    @RequestMapping(value="/add", produces = {"application/json;charset=UTF-8"})
    public RouteHistory addStudent(@RequestBody RouteHistory routeHistory){
        routeHistory= routeHistoryRepository.save(routeHistory);
        return routeHistory;
    }

    @RequestMapping(value="/delete")
    public String delete(@RequestParam Integer id){
        routeHistoryRepository.deleteById(id);
        return "delete success";
    }

    @RequestMapping(value="/findByUserIdAndStartTime")
    public ResponseEntity findByUserIdAndStartTime(@RequestParam Integer userId, @RequestParam String tripStartTime) throws ParseException {

        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tripStartTime);
        List<RouteHistory> all = routeHistoryRepository.findAll(userId, parse);
        if(!all.isEmpty()){
            return ResponseEntity.ok(all.get(0));
        }
        return null;
    }
}
