package com.tus.FleetManagement.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tus.FleetManagement.Entity.Bus;

@RestController
@RequestMapping(path = "/api/v1")
public class BusController {

	
	private final BusService busService;
	
	@Autowired
	public BusController(BusService busService) {
		this.busService = busService;
	}
	
	
	
	@RequestMapping(value = "/bus" ,method = RequestMethod.GET)
	public List<Bus> getAllBus()
	{
		return busService.getAllBus();
	}
	
	@RequestMapping(value = "/bus/{id}", method = RequestMethod.GET)
	public Optional<Bus> getBus(@PathVariable int id)
	{
		return busService.getBus(id);
	}
	
	
	
	
	@RequestMapping(value ="/bus", method = RequestMethod.POST)
	public ResponseEntity<Bus> createBus(@RequestBody Bus bus)
	{
		System.out.println(bus);
		Bus savedBus = busService.addBus(bus);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/bus").buildAndExpand(savedBus.getBusId()).toUri();
		return new ResponseEntity<Bus>(savedBus,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/bus/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Bus> updateBus(@PathVariable int id, @RequestBody Bus bus)
	{
		Bus updatedBus = busService.updateBus(id, bus);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/bus").buildAndExpand(updatedBus.getBusId()).toUri();
		return new ResponseEntity<Bus>(updatedBus,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/bus/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Bus> updateBus(@PathVariable int id)
	{
		System.out.println("start");
		Bus deletedBus = busService.deleteBus(id);
		System.out.println("out");
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.OK);
	}

}
