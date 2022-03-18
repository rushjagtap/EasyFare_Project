package com.tus.FleetManagement.Controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tus.FleetManagement.Entity.Bus;
import com.tus.FleetManagement.Entity.PassengerInfo;
import com.tus.FleetManagement.dao.BusRepository;
import com.tus.FleetManagement.dao.PassengerRepository;
import com.tus.FleetManagement.DTO.PassengerDTO;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	public String checkPassenger(PassengerDTO passenger) {
		Bus driverInfo= busRepo.findDistinctByBusNumberAndRouteNumber(passenger.getBusNumber(), passenger.getRouteNumber());
		long driverId=driverInfo.getDriverId();
		Integer userid=passenger.getUserid();
		if(passRepo.existsById(userid)) {
			passRepo.deleteById(userid);
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
