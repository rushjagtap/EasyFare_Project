package com.tus.farecalculation.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tus.farecalculation.entry.*;
import com.tus.farecalculation.mapper.RouteHistoryRepository;
import com.tus.farecalculation.mapper.RouteInformationRepository;
import com.tus.farecalculation.mapper.StopInformationRepository;
import com.tus.farecalculation.vo.FleetInformation;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/stopInformation")
public class StopInformationController {

    @Autowired
    StopInformationRepository stopInformationRepository;

    @Autowired
    private RouteInformationRepository routeInformationRepository;

    @Autowired
    private RouteHistoryRepository  routeHistoryRepository;

    @Autowired
    private Deduct deductService;


    @Autowired
    private Locations locationService;





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
        List<StopInformation> startformationList= stopInformationRepository.findAll(routeInformationId);
        if(stopInformationList.isEmpty()){
            throw  new Exception("no start location ");
        }

        //calculate peaktime
       String routeId = fleetInformation.getRouteNum();
        Date startTime = fleetInformation.getStartTime();
        int year = startTime.getYear();
        int month = startTime.getMonth();
        int hours = startTime.getHours();
        Integer numOfPass = fleetInformation.getNumOfPass();
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        RestTemplate restTemplate= new RestTemplate();
        PeakTime peakTime = new PeakTime(routeInformationId,month,year,hours,dayForWeek,2);
        String restObj=restTemplate.postForObject("http://54.227.57.147:5000/predictpeaktime", peakTime, String.class);
        System.out.println("restObj "+restObj);
        //calculate the distance
        StopInformation startInfomation = startformationList.get(0);
        StopPointsDTO stopPointsDTO = new StopPointsDTO();
        ArrayList<DataPointsDTO> dataPointsDTOS = new ArrayList<>();
        DataPointsDTO dataPointsDTO = new DataPointsDTO();
        dataPointsDTO.setLongitude(stopInformation.getLongitude());
        dataPointsDTO.setLatitude(stopInformation.getLatitude());
        DataPointsDTO dataPointsDTO2 = new DataPointsDTO();
        dataPointsDTO2.setLongitude(startInfomation.getLongitude());
        dataPointsDTO2.setLatitude(startInfomation.getLatitude());
        dataPointsDTOS.add(dataPointsDTO);
        dataPointsDTOS.add(dataPointsDTO2);
        stopPointsDTO.setPointsList(dataPointsDTOS);

        String distance = "";
        try{
            distance= locationService.getDistance(stopPointsDTO).getBody().getMessgae();
            System.out.println(distance);
        }catch(FeignException e){
            // distance = e.getMessage().substring(137, e.getMessage().length() - 3);
        }
        System.out.println("distance+++++++++++++++++"+distance);
        BigDecimal price = stopInformation.getPrice();
        //priceCalculate
        Integer stopInformationId = stopInformation.getId();
        BigDecimal beforePrice=stopInformationRepository.findAll(stopInformationId,stopInformation.getId());
        if(beforePrice!=null){
            price=price.add(beforePrice);
        }
        if(!restObj.isEmpty()&&restObj.equals("1")){
            price=price.multiply(new BigDecimal(1.1));
        }
        RouteHistory routeHistory = new RouteHistory();
        routeHistory.setUserId(fleetInformation.getUserId());
        routeHistory.setBusNum(fleetInformation.getBusNum());
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
        FareDTO fareDTO = new FareDTO();
        fareDTO.setDestination(fleetInformation.getDropringName());
        fareDTO.setFare(price.longValue());
        fareDTO.setSource(fleetInformation.getBoardingName());
        String messgae = deductService.deductFareFromCard(fleetInformation.getUserId(), fareDTO).getBody().getMessgae();
        return ResponseEntity.ok(messgae);
    }

    public static void main(String[] args) {
        FleetInformation fleetInformation = new FleetInformation();
        fleetInformation.setBoardingName("athlone");
        fleetInformation.setBusNum("222");
        fleetInformation.setDriverId(22);
        fleetInformation.setDropringName("dublin");
        fleetInformation.setRouteNum("111");
        fleetInformation.setEndTime(new Date());
        fleetInformation.setStartTime(new Date());
        fleetInformation.setUserId(1);
        String object = JSONObject.toJSONString(fleetInformation);
        System.out.println(object);
    }
}
