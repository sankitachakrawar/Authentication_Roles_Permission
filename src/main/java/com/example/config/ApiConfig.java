package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.utils.ApiLoggerInterceptor;

@Configuration
public class ApiConfig implements WebMvcConfigurer{

	@Autowired
	private ApiLoggerInterceptor apiLoggerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(apiLoggerInterceptor);
	}
	
}
