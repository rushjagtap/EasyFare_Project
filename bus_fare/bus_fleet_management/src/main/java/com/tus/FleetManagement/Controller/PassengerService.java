package com.tus.FleetManagement.Controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tus.FleetManagement.Entity.Bus;
import com.tus.FleetManagement.Entity.PassengerInfo;
import com.tus.FleetManagement.dao.BusRepository;
import com.tus.FleetManagement.dao.PassengerRepository;
import com.tus.FleetManagement.DTO.FleetInformationDTO;
import com.tus.FleetManagement.DTO.PassengerDTO;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${locationUrl}")
	String locationUrl;
	
	public String checkPassenger(PassengerDTO passenger) {
		
		System.out.println("in check service");
		//Bus driverInfo= busRepo.findDistinctByBusNumberAndRouteNumber(passenger.getBusNumber(), passenger.getRouteNumber());
		//long driverId=driverInfo.getDriverId();
		int driverId = 1;
		Integer userid=passenger.getUserid();
		if(passRepo.existsById(userid)) {
			System.out.println("user exists");
			passRepo.deleteById(userid);
			
		
			
			FleetInformationDTO passObj= new FleetInformationDTO(1, 1, 1, 1, "boardingName", "dropringName", null,null);
			System.out.println("calling fare calculation");
			String restObj=restTemplate.postForObject(locationUrl, passObj, String.class);
			System.out.println(restObj+" Call successfull");
			
			return "User dropped the bus at "+passenger.getStartpoint();
		}else {
			System.out.println("User not present in table");
			LocalDateTime startTime=LocalDateTime.now();
			PassengerInfo passEntity= new PassengerInfo(passenger.getUserid(), startTime, passenger.getStartpoint(), passenger.getRouteNumber(), passenger.getBusNumber(), driverId, passenger.getAvailableBalance());
			passRepo.save(passEntity);
			return "User boarded the bus from "+passenger.getStartpoint();
		}
		
	}
}
