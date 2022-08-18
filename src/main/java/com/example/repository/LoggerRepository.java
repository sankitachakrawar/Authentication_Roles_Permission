package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.LoggerEntity;



@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
	

	LoggerEntity findByToken(String token);

	void removeByToken(String token);

	
}
