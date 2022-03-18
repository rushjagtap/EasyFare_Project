package com.mall.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mall.entry.RouteHistory;
import com.mall.entry.RouteInformation;
import com.mall.entry.StopInformation;
import com.mall.mapper.RouteHistoryRepository;
import com.mall.mapper.RouteInformationRepository;
import com.mall.mapper.StopInformationRepository;
import com.mall.vo.FleetInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/stopInformation")
public class StopInformationController {

    @Autowired
    StopInformationRepository stopInformationRepository;

    @Autowired
    private RouteInformationRepository routeInformationRepository;

    @Autowired
    private RouteHistoryRepository  routeHistoryRepository;





    @GetMapping("/getAll")
    public List<StopInformation> queryStudent(){
        List<StopInformation> list ;
        list = stopInformationRepository.findAll();
        return list  ;
    }


    @PostMapping(value="/add", produces = {"application/json;charset=UTF-8"})
    public StopInformation add(@RequestBody StopInformation stopInformation){
        stopInformation= stopInformationRepository.save(stopInformation);
        return stopInformation;
    }

    @PostMapping(value="/ReceiveInAndCalculatePriceAndSaveHistory", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity ReceiveInAndCalculatePriceAndSaveHistory(@RequestBody FleetInformation fleetInformation) throws Exception {

       List<RouteInformation> routeInformationList=routeInformationRepository.findAll(fleetInformation.getRouteNum(),fleetInformation.getBusNum());
        if(routeInformationList.isEmpty()){
            throw  new Exception("no store route Information");
        }
        RouteInformation routeInformation = routeInformationList.get(0);
        Integer routeInformationId = routeInformation.getId();
        List<StopInformation> stopInformationList=stopInformationRepository.findAll(routeInformationId,fleetInformation.getDropringName());

        if(stopInformationList.isEmpty()){
            throw  new Exception("no store stop Infomation");
        }

        StopInformation stopInformation = stopInformationList.get(0);
        BigDecimal price = stopInformation.getPrice();
        //priceCalculate
        Integer stopInformationId = stopInformation.getId();
        BigDecimal beforePrice=stopInformationRepository.findAll(stopInformationId,stopInformation.getId());
        if(beforePrice!=null){
            price=price.add(beforePrice);
        }
        RouteHistory routeHistory = new RouteHistory();
        routeHistory.setUserId(fleetInformation.getUserId());
        routeHistory.setBusId(fleetInformation.getBusNum());
        routeHistory.setDriverId(fleetInformation.getDriverId());
        routeHistory.setTripStartTime(fleetInformation.getStartTime());
        routeHistory.setTripEndTime(fleetInformation.getEndTime());
        routeHistory.setRouteNumber(fleetInformation.getRouteNum());
        routeHistory.setBoardingPoint(fleetInformation.getBoardingName());
        routeHistory.setDropOffPoint(fleetInformation.getDropringName());
        routeHistory.setCost(price);
        routeHistory.setCreateTime(new Date());
        routeHistoryRepository.save(routeHistory);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("source",fleetInformation.getBoardingName());
        resultMap.put("destination",fleetInformation.getDropringName());
        resultMap.put("userId",fleetInformation.getUserId());
        resultMap.put("fare",price);
        return ResponseEntity.ok(resultMap);
    }

    public static void main(String[] args) {
        FleetInformation fleetInformation = new FleetInformation();
        fleetInformation.setBoardingName("athlone");
        fleetInformation.setBusNum(222);
        fleetInformation.setDriverId(22);
        fleetInformation.setDropringName("dublin");
        fleetInformation.setRouteNum(1);
        fleetInformation.setEndTime(new Date());
        fleetInformation.setStartTime(new Date());
        fleetInformation.setUserId(1);
        String object = JSONObject.toJSONString(fleetInformation);
        System.out.println(object);
    }
}
