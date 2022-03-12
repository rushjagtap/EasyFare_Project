package com.tus.easyfare.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tus.easyfare.DTO.HateoesRespDTO;
import com.tus.easyfare.DTO.ResponseDTO;
import com.tus.easyfare.DTO.SmartCardDTO;
import com.tus.easyfare.entity.SmartCardEntity;
import com.tus.easyfare.entity.UserEntity;
import com.tus.easyfare.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class SmartCardController {
	
	@Autowired
	private UserRepository userRepo;

	//to get smartcard details using userid
	@RequestMapping(value = "user/{userid}/carddetail", method = RequestMethod.GET)
	public ResponseEntity<?> getCardDetails(@PathVariable Integer userid) {
		UserEntity userEntity=userRepo.findById(userid).get();
		SmartCardEntity smartCardDetails=userEntity.getSmartCard();
		Object obj= smartCardDetails;
		Map<String, ArrayList<URI>> objMap = new HashMap<>();
		ArrayList<URI> putList= new ArrayList<>();
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().
				buildAndExpand(smartCardDetails.getCardId()).toUri();
		putList.add(location);
		objMap.put("PUT", putList);
		HateoesRespDTO hateosObj= new HateoesRespDTO(obj, objMap);
		 return new ResponseEntity<>(hateosObj, HttpStatus.OK);
	}
	
	//to update the smartcard details
	@RequestMapping(value = "user/{userid}/carddetail", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCardDetails(@PathVariable Integer userid,@RequestBody @Valid SmartCardDTO cardDetails) {
		UserEntity userEntity=userRepo.findById(userid).get();
		SmartCardEntity smartcardDetails= SmartCardEntity.builder().cardId(userEntity.getSmartCard().getCardId()).cardNum(cardDetails.getCardNum()).cardStatus(cardDetails.getCardStatus()).dateOfReg(cardDetails.getDateOfReg()).dateOfExp(cardDetails.getDateOfExp()).balance(cardDetails.getBalance()).build();
		userEntity.setSmartCard(smartcardDetails);
		UserEntity resp=userRepo.save(userEntity);
		Object obj= resp;
		Map<String, ArrayList<URI>> objMap = new HashMap<>();
		ArrayList<URI> putList= new ArrayList<>();
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().
				buildAndExpand(resp.getSmartCard().getCardId()).toUri();
		putList.add(location);
		objMap.put("PUT", putList);
		HateoesRespDTO hateosObj= new HateoesRespDTO(obj, objMap);
		 return new ResponseEntity<>(hateosObj, HttpStatus.OK);
	}
	
	//to topup the user smartcard
	@RequestMapping(value = "user/{userid}/topup", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> updateCardDetails(@PathVariable Integer userid,@RequestParam Integer amount) {
		UserEntity userEntity=userRepo.findById(userid).get();
		long currentBalance=userEntity.getSmartCard().getBalance();
		long newBalance=currentBalance+amount;
		userEntity.getSmartCard().setBalance(newBalance);
		UserEntity resp=userRepo.save(userEntity);
		String msg="User "+resp.getFirstName()+ " with user id "+userid+" successfully recharged with "+amount+".Current available balance:"+ resp.getSmartCard().getBalance();
		ResponseDTO respObj= new ResponseDTO(msg);
		return new ResponseEntity<ResponseDTO>(respObj, HttpStatus.OK);
	}
}
