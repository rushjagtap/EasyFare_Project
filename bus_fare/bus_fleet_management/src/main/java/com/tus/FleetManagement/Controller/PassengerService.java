package com.tus.FleetManagement.Controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tus.FleetManagement.Entity.Bus;
import com.tus.FleetManagement.Entity.PassengerInfo;
import com.tus.FleetManagement.dao.BusRepository;
import com.tus.FleetManagement.dao.PassengerRepository;

import antlr.collections.List;

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
		Optional<Bus> currentBus=busRepo.findDistinctByBusNumberAndRouteNumber(passenger.getBusNumber(), passenger.getRouteNumber());
		if (currentBus.isPresent() == false)
		{
			return "No such Bus and Route";
			
		}
		else
		{
			System.out.println(passenger);
			System.out.println(passenger.getStartpoint());
			Integer userid=passenger.getUserid();
			Optional<PassengerInfo> currentPassenger = passRepo.findById(userid);
			if(passRepo.existsById(userid)) 
			{
				System.out.println("user exists");
				passRepo.deleteById(userid);
				
			
				LocalDateTime startTime= currentPassenger.get().getStartTime();
				LocalDateTime localDateTime= LocalDateTime.parse(startTime.toString());
				Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
				Date startTimeDate = Date.from(instant);
				System.out.println("Start time is "+startTimeDate);
				Integer passCount= passRepo.findByRouteNumberAndBusNumber(passenger.getRouteNumber(),passenger.getBusNumber());
				System.out.println("Passenger count "+passCount);
				FleetInformationDTO passObj= new FleetInformationDTO(userid, passenger.getRouteNumber(), passenger.getBusNumber(),currentBus.get().getDriverId() ,currentPassenger.get().getStart_point(), passenger.getStartpoint(), startTimeDate,new Date(),passCount);
				System.out.println("calling fare calculation");
				System.out.println("calling fare calculation" + locationUrl + " data:  "+passObj);
				String restObj=restTemplate.postForObject(locationUrl, passObj, String.class);
				System.out.println(restObj+" Call successfull");
				System.out.println(restObj.substring(restObj.indexOf("of"),restObj.indexOf("done")));
				return "User dropped the bus at "+passenger.getStartpoint() + "." +restObj ;
				
			}
			else 
			{
					System.out.println("User not present in table");
					LocalDateTime startTime=LocalDateTime.now();
					PassengerInfo passEntity= new PassengerInfo(passenger.getUserid(), startTime, passenger.getStartpoint(), "",  passenger.getRouteNumber(), passenger.getBusNumber(), currentBus.get().getDriverId(), passenger.getAvailableBalance());
					passRepo.save(passEntity);
					return "User boarded the bus from "+passenger.getStartpoint();
			}
		
	}
	}
}

