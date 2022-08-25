package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.OtpLoggerDto;
import com.example.entities.OtpLogger;
import com.example.entities.UserEntity;
import com.example.repository.OtpLoggerRepository;
import com.example.service.OtpLoggerService;

@Service
public class OtpLoggerServiceImpl implements OtpLoggerService{

	@Autowired
	private OtpLoggerRepository loggerRepository;
	
	@Override
	public void createLogger(OtpLoggerDto loggerDto, UserEntity user) {

		OtpLogger logger = new OtpLogger();
		logger.setId(user);
		logger.setOtp(loggerDto.getOtp());
		logger.setCreatedAt(loggerDto.getCreatedAt());
		logger.setExpireAt(loggerDto.getExpireAt());
		loggerRepository.save(logger);

	}
}
