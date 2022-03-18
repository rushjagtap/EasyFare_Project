package com.tus.usermanagement.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.Servlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tus.usermanagement.DTO.HateoesRespDTO;
import com.tus.usermanagement.DTO.UserDTO;
import com.tus.usermanagement.DTO.UserTransferDTO;
import com.tus.usermanagement.entity.UserEntity;
import com.tus.usermanagement.repository.UserRepository;
import com.tus.usermanagement.service.UserService;

@CrossOrigin("*")
@RestController
@Validated
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	// to insert into user table
	@RequestMapping(value = "/user",method = RequestMethod.POST)
	public ResponseEntity<?> insertUser(@RequestBody @Valid UserDTO user){
		UserTransferDTO userDetails=userService.insertUser(user);
		Object obj= userDetails;
		Map<String, ArrayList<URI>> objMap = new HashMap<>();
		ArrayList<URI> getList= new ArrayList<>();
		ArrayList<URI> putList= new ArrayList<>();
		ArrayList<URI> deleteList= new ArrayList<>();
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userDetails.getUserid()).toUri();
		URI location2= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/carddetail")
				.buildAndExpand(userDetails.getUserid()).toUri();
		getList.add(location);
		getList.add(location2);
		putList.add(location);
		putList.add(location2);
		deleteList.add(location);
		objMap.put("GET", getList);
		objMap.put("PUT", putList);
		objMap.put("DELETE", deleteList);
		HateoesRespDTO hateosObj= new HateoesRespDTO(obj, objMap);
		return new ResponseEntity<>(hateosObj, HttpStatus.CREATED);
	}
	
	//to fetch all the user
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getUserDetails() {
		List<UserEntity> ueList=userRepo.findAll();
		
		 return new ResponseEntity<List<UserEntity>>(ueList, HttpStatus.OK);
	}
	
	//to fetch the user according to the id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserDetailsById(@PathVariable Integer id) {
		Optional<UserEntity> userList=userRepo.findById(id);
		Object obj= userList;
		Map<String, ArrayList<URI>> objMap = new HashMap<>();
		ArrayList<URI> getList= new ArrayList<>();
		ArrayList<URI> putList= new ArrayList<>();
		ArrayList<URI> deleteList= new ArrayList<>();
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(userList.get().getUserid()).toUri();
		URI location2= ServletUriComponentsBuilder.fromCurrentRequest().path("/carddetail")
				.buildAndExpand(userList.get().getUserid()).toUri();
		getList.add(location2);
		putList.add(location);
		putList.add(location2);
		deleteList.add(location);
		objMap.put("GET", getList);
		objMap.put("PUT", putList);
		objMap.put("DELETE", deleteList);
		HateoesRespDTO hateosObj= new HateoesRespDTO(obj, objMap);
		 return new ResponseEntity<>(hateosObj, HttpStatus.OK);
	}
	//to update the user
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserDetailsById(@PathVariable Integer id,@RequestBody @Valid UserDTO user) {
		Optional<UserEntity> userList=userRepo.findById(id);
		UserTransferDTO userDetails=userService.updateUser(userList,user);
		Object obj= userDetails;
		Map<String, ArrayList<URI>> objMap = new HashMap<>();
		ArrayList<URI> getList= new ArrayList<>();
		ArrayList<URI> putList= new ArrayList<>();
		ArrayList<URI> deleteList= new ArrayList<>();
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(userList.get().getUserid()).toUri();
		URI location2= ServletUriComponentsBuilder.fromCurrentRequest().path("/carddetail")
				.buildAndExpand(userList.get().getUserid()).toUri();
		getList.add(location2);
		getList.add(location);
		putList.add(location2);
		deleteList.add(location);
		objMap.put("GET", getList);
		objMap.put("PUT", putList);
		objMap.put("DELETE", deleteList);
		HateoesRespDTO hateosObj= new HateoesRespDTO(obj, objMap);
		return new ResponseEntity<>(hateosObj, HttpStatus.OK); 
	}

	//to delete the user
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserEntity> deleteUserDetailsById(@PathVariable Integer id) {
		Optional<UserEntity> userList=userRepo.findById(id);
		UserEntity existingUser= userList.get();
		userRepo.delete(existingUser);
		return new ResponseEntity<UserEntity>(existingUser, HttpStatus.OK);
	}

	
}
