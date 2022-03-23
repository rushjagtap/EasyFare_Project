package com.tus.FleetManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tus.FleetManagement.DTO.FeatureDTO;
import com.tus.FleetManagement.DTO.RootDTO;
import com.tus.FleetManagement.Entity.Bus;
import com.tus.FleetManagement.Entity.StopInfo;
import com.tus.FleetManagement.dao.BusRepository;
import com.tus.FleetManagement.dao.RouteRepository;
import com.tus.FleetManagement.dao.StopInfoRepository;

@Service
public class RouteService {
	
	@Autowired
	private StopInfoRepository stopInfoRepo;
	
	@Autowired
	private BusRepository busRepo;
	
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
			stopData.setDistance(1.0);
			stopData.setPrice(feature.getProperties().getFare());
			stopList.add(stopData);
		}
		stopInfoRepo.saveAll(stopList);
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
