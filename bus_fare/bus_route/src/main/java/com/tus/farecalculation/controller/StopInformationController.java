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
import org.springframework.beans.factory.annotation.Value;
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


    @Value("${fare_mode}")
    private String  fareMode;


    @Value("${price_per_km}")
    private double  basePrice;

    @Value("${peak_hour_percentage}")
    private double peakHourPercentage;

    @Value("${peak_time_url}")
    private String pickTimeUrl;


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
        if(stopInformationList.isEmpty()){
            throw  new Exception("no start location ");
        }
        //calculate peaktime
        String ifPeakTime = calculatePeakTime(fleetInformation, routeInformationId);

        Double price = stopInformation.getPrice();
        if(fareMode.equals("distance_based")){
           Double distance= calculateDistance(stopInformation);
           System.out.println("distance is:"+distance);
            price=basePrice*distance;
            System.out.println("Price is:"+price);
        }
        else if(fareMode.equals("stop_based")){
            //priceCalculate
            Integer stopInformationId = stopInformation.getId();
            Double beforePrice=stopInformationRepository.findAll(stopInformationId,stopInformation.getRouteInformationId(),fleetInformation.getBoardingName());
            if(beforePrice!=null){
                price=price+beforePrice;
            }
        }
        //if peak time price *1.1
        if(!ifPeakTime.isEmpty()&&ifPeakTime.equals("1")){
            price=price*peakHourPercentage;
            BigDecimal   b   =   new   BigDecimal(price);
            price  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        if(fleetInformation.getBoardingName().equals(fleetInformation.getDropringName())){
            price=0.0;
        }
        // save history
        saveHistory(fleetInformation,price,ifPeakTime);
        FareDTO fareDTO = new FareDTO();
        fareDTO.setDestination(fleetInformation.getDropringName());
        fareDTO.setFare(price);
        System.out.println("Fare is :"+fareDTO.getFare());
        fareDTO.setSource(fleetInformation.getBoardingName());
        String messgae = deductService.deductFareFromCard(fleetInformation.getUserId(), fareDTO).getBody().getMessgae();
        return ResponseEntity.ok(messgae);
    }

    private void saveHistory(FleetInformation fleetInformation, Double price,String ifPeaktime) {
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
        if(!ifPeaktime.isEmpty()){
            routeHistory.setIfPeakHour(Integer.valueOf(ifPeaktime));
        }
        routeHistoryRepository.save(routeHistory);
    }

    private Double calculateDistance(StopInformation stopInformation) {
        //calculate the distance
        List<StopInformation> stopInformationList2 = stopInformationRepository.findAll(stopInformation.getRouteInformationId());
        StopPointsDTO stopPointsDTO = new StopPointsDTO();
        ArrayList<DataPointsDTO> dataPointsDTOS = new ArrayList<>();
        for(StopInformation temp:stopInformationList2){
            DataPointsDTO dataPointsDTO = new DataPointsDTO();
            dataPointsDTO.setLatitude(temp.getLatitude());
            dataPointsDTO.setLongitude(temp.getLongitude());
            dataPointsDTOS.add(dataPointsDTO);
        }

        stopPointsDTO.setPointsList(dataPointsDTOS);
        Double  distance= locationService.getDistance(stopPointsDTO);
        return distance;
    }

    private String calculatePeakTime(FleetInformation fleetInformation,Integer routeInformationId) {
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
        PeakTime peakTime = new PeakTime(routeInformationId,month,year,hours,dayForWeek,fleetInformation.getNumOfPass());
        String restObj=restTemplate.postForObject(pickTimeUrl, peakTime, String.class);
        return restObj;
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
