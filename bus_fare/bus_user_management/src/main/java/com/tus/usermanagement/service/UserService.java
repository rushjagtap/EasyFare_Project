package com.tus.usermanagement.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.tus.usermanagement.DTO.UserDTO;
import com.tus.usermanagement.DTO.UserTransferDTO;
import com.tus.usermanagement.entity.SmartCardEntity;
import com.tus.usermanagement.entity.UserEntity;
import com.tus.usermanagement.repository.CardRepository;
import com.tus.usermanagement.repository.UserRepository;

@Service
@Validated
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CardRepository cardRepository;
	
	
	public UserTransferDTO insertUser(@RequestBody UserDTO user) {
		String ageGroup=FindAgeGroup(user.getDob());
		UserEntity saveUser= new UserEntity();
		saveUser.setFirstName(user.getFirstName());
		saveUser.setSecondName(user.getSecondName());
		saveUser.setDob((java.sql.Date) user.getDob());
		saveUser.setMobNo(user.getMobNo());
		System.out.println("age Group is :"+ageGroup);
		saveUser.setAgeGroup(ageGroup);
		saveUser.setEmailId(user.getEmailId());
		System.out.println("Card Number is: "+user.getCardNumber());
		LocalDate expDateLocal=user.getDateOfReg().toLocalDate().plusYears(2); 
		SmartCardEntity cardEntity = new SmartCardEntity(user.getCardNumber(), user.getDateOfReg(), Date.valueOf(expDateLocal), user.getBalance(), user.getCardStatus());
		saveUser.setSmartCard(cardEntity);
		UserEntity savedUser=userRepo.save(saveUser);
		//cardEntity.setUserEntity(saveUser);
		//cardRepository.save(cardEntity);
		return new UserTransferDTO(saveUser);
	}
	
	public UserTransferDTO updateUser(Optional<UserEntity> userList, UserDTO user) {
		String ageGroup=FindAgeGroup(user.getDob());
		UserEntity updateUser= userList.get();
		updateUser.setFirstName(user.getFirstName());
		updateUser.setSecondName(user.getSecondName());
		updateUser.setDob((java.sql.Date) user.getDob());
		updateUser.setMobNo(user.getMobNo());
		System.out.println("age Group is :"+ageGroup);
		updateUser.setAgeGroup(ageGroup);
		updateUser.setEmailId(user.getEmailId());
		System.out.println("Card Number is: "+user.getCardNumber());
		LocalDate expDateLocal=user.getDateOfReg().toLocalDate().plusYears(2);
		SmartCardEntity cardEntity = new SmartCardEntity();
		cardEntity.setCardId(updateUser.getSmartCard().getCardId());
		cardEntity.setBalance(user.getBalance());
		cardEntity.setCardNum(user.getCardNumber());
		cardEntity.setCardStatus(user.getCardStatus());
		cardEntity.setDateOfReg(user.getDateOfReg());
		cardEntity.setDateOfExp(Date.valueOf(expDateLocal));
		updateUser.setSmartCard(cardEntity);
		UserEntity savedUser=userRepo.save(updateUser);
		//cardEntity.setUserEntity(saveUser);
		//cardRepository.save(cardEntity);
		return new UserTransferDTO(savedUser);
	}
	
	private String FindAgeGroup(Date dob) {
		String ageGroup;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String current_date_str=sdf.format(Calendar.getInstance().getTime());
		LocalDate curr_date= LocalDate.parse(current_date_str);
		LocalDate dob_date=dob.toLocalDate();
		Period period= dob_date.until(curr_date);
		int age=period.getYears();
		if(age<=18) {
			ageGroup="STUDENT";
		}else if(age>18 && age<=59) {
			ageGroup="ADULT";
		}else {
			ageGroup="SENIOR";
		}
		return ageGroup;
	}
}
