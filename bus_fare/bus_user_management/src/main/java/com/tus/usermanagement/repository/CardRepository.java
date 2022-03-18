package com.tus.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.usermanagement.entity.SmartCardEntity;
import com.tus.usermanagement.entity.SmartCardKey;

public interface CardRepository extends JpaRepository<SmartCardEntity, Integer>{
	List<SmartCardEntity> findAll();
	
}
