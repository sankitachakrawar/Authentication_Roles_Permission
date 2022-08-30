package com.example.service;


import java.util.List;


import com.example.dto.LoggerDto;
import com.example.entities.LoggerEntity;
import com.example.entities.UserEntity;


public interface LoggerServiceInterface {

	void createLogger(LoggerDto loggerDto, UserEntity user);

	//void logoutUser(String token);

	List<LoggerEntity> getAllDetails();

	void logoutUser(String token);
	
}





