package com.tus.FleetManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tus.FleetManagement.DTO.DistanceClacDTO;
import com.tus.FleetManagement.DTO.FeatureDTO;
import com.tus.FleetManagement.DTO.PointsListDTO;
import com.tus.FleetManagement.DTO.RootDTO;
import com.tus.FleetManagement.Entity.Bus;
import com.tus.FleetManagement.Entity.StopInfo;
import com.tus.FleetManagement.dao.BusRepository;
import com.tus.FleetManagement.dao.StopInfoRepository;

@Service
public class RouteService {
	
	@Autowired
	private StopInfoRepository stopInfoRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void insertToStopInfo(Integer routeId,RootDTO routeJson) {
		
		List<StopInfo> stopList= new ArrayList<>();
		ArrayList<FeatureDTO> featureList= routeJson.getFeatures();
		Integer stopCount=0;
		for (FeatureDTO feature:featureList) {
			stopCount++;
			StopInfo stopData= new StopInfo();
			stopData.setRouteInfoId(routeId);
			stopData.setStopNumber(stopCount);
			stopData.setStopName(feature.getProperties().getName());
			stopData.setLongitude(feature.getGeometry().getCoordinates().get(0));
			stopData.setLatitude(feature.getGeometry().getCoordinates().get(1));
			Double distance=0.0;
			if(stopCount>1) {
				distance=calculateDistance(featureList.get(stopCount-2).getGeometry().getCoordinates().get(1),featureList.get(stopCount-2).getGeometry().getCoordinates().get(0),feature.getGeometry().getCoordinates().get(1),feature.getGeometry().getCoordinates().get(0));
			}
			stopData.setDistance(distance);
			stopData.setPrice(feature.getProperties().getFare());
			stopList.add(stopData);
		}
		stopInfoRepo.saveAll(stopList);
	}

	private Double calculateDistance(Double oldLat, Double oldLong, Double currLat, Double curLong) {
		PointsListDTO pointsOld= new PointsListDTO();
		System.out.println("oldLat: "+oldLat);
		System.out.println("oldLong: "+oldLong);
		System.out.println("currLat: "+currLat);
		System.out.println("oldLong: "+curLong);
		pointsOld.setLatitude(oldLat);
		pointsOld.setLongitude(oldLong);
		PointsListDTO pointsNew = new PointsListDTO();
		pointsNew.setLatitude(currLat);
		pointsNew.setLongitude(curLong);
		ArrayList<PointsListDTO> pointsArrayList = new ArrayList<>();
		pointsArrayList.add(pointsOld);
		pointsArrayList.add(pointsNew);
		System.out.println("pointsArrayList: "+pointsArrayList);
		DistanceClacDTO distancObj= new DistanceClacDTO();
		distancObj.setPointsList(pointsArrayList);
		Double distance=restTemplate.postForObject("http://distance-tracker-microservice/api/v1/locations", distancObj, Double.class);
		return distance;
		
	}

	public void insertToBus(String routeNum, RootDTO routeJson, String busNum) {
		// TODO Auto-generated method stub
		Bus busObj= new Bus();
		busObj.setBusNumber(busNum);
		busObj.setBusStatus("ACTIVE");
		busObj.setDriverId("111");
		busObj.setStartStation(routeJson.getFeatures().get(0).getProperties().getName());
		Integer len=routeJson.getFeatures().size();
		busObj.setEndPoint(routeJson.getFeatures().get(len-1).getProperties().getName());
		busObj.setRouteNumber(routeNum);
		busRepo.save(busObj);
		
	}

}
