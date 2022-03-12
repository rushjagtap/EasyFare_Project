package com.tus.EasyFare.FleetManagement.Bus.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tus.EasyFare.FleetManagement.Bus.Entity.Bus;
import com.tus.EasyFare.FleetManagement.Bus.dao.BusRepository;


@Service
public class BusService {
	
	private final BusRepository busRepository;
	
	@Autowired
	public BusService(BusRepository busRepository)
	{
		this.busRepository = busRepository;
		
	}
	
	public List<Bus> getAllBus()
	{
		return busRepository.findAll();
	}
	
	public Optional<Bus> getBus(long id)
	{
		return busRepository.findById(id);
	}
	
	
	public Bus addBus(Bus bus)
	{
		return busRepository.save(bus);
	}
	
	public Bus deleteBus(long id)
	{
		System.out.println("test");
		Optional<Bus> optionalBus = busRepository.findById(id);
		System.out.println(optionalBus);
		Bus existingBus = optionalBus.get();
		busRepository.deleteById(id);
		return existingBus;
	}
	
	public Bus updateBus(long id, Bus bus)
	{
		
		Optional<Bus> optionalBus = busRepository.findById(id);
		
		Bus existingBus = optionalBus.get();
		System.out.println("test");
		System.out.println(existingBus);
		existingBus.setBusNumber(bus.getBusNumber());
		existingBus.setBusStatus(bus.getBusStatus());
		existingBus.setCity(bus.getCity());
		existingBus.setDriverId(bus.getDriverId());
		existingBus.setEndPoint(bus.getEndPoint());
		existingBus.setRouteNumber(bus.getRouteNumber());
		existingBus.setStartStation(bus.getStartStation());
		
		return busRepository.save(existingBus);
	}

}
