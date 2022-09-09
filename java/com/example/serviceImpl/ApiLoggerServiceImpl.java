package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ApiLogger;
import com.example.repository.ApiLoggerRepository;
import com.example.service.ApiLoggerService;
@Service
public class ApiLoggerServiceImpl implements ApiLoggerService{

	@Autowired
	private ApiLoggerRepository apiLoggerRepository;
	
	@Override
	public void createApiLogger(ApiLogger apiLogger) {
		
		apiLoggerRepository.save(apiLogger);
	}

}
