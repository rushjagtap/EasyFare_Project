package com.tus.FleetManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tus.FleetManagement.DTO.RootDTO;
import com.tus.FleetManagement.Entity.Route;
import com.tus.FleetManagement.Exception.RouteExistException;
import com.tus.FleetManagement.dao.RouteRepository;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin("*")
public class RouteController {
	
	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private RouteService routeService;

	@RequestMapping(value = "/createroute",method = RequestMethod.POST)
	public ResponseEntity<Object> createRoute(@RequestParam String busnum, @RequestParam String routenum,@RequestBody RootDTO routeJson) {
		List<Route> routesList=routeRepo.findByRouteNumAndBusNum(routenum, busnum);
		if(routesList.isEmpty()) {
			Route routeInfo= new Route();
			routeInfo.setRouteNum(routenum);
			routeInfo.setBusNum(busnum);
			Route savedEntity=routeRepo.save(routeInfo);
			routeService.insertToStopInfo(savedEntity.getRouteid(), routeJson);
			routeService.insertToBus(routenum, routeJson,busnum);
			Map<String, String> respObj= new HashMap<>();
			respObj.put("Message", "Route and bus added successfully");
			return new ResponseEntity<Object>(respObj, HttpStatus.OK);
		}else {
			throw new RouteExistException();
		}
	}
}
