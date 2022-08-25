package com.example.service;


import com.example.dto.OtpLoggerDto;
import com.example.entities.UserEntity;

public interface OtpLoggerService {

	void createLogger(OtpLoggerDto loggerDto, UserEntity user);
}
