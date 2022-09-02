package com.example.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.entities.ApiLogger;
import com.example.service.ApiLoggerService;


@Component
public class ApiLoggerInterceptor implements HandlerInterceptor{

	@Autowired
	private ApiLoggerService apiLoggerService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Pre Handle method is Calling");

		ApiLogger apiLogger=new ApiLogger();
		
		apiLogger.setMethod(request.getMethod());
		apiLogger.setUrl(request.getRequestURI());
		apiLogger.setToken(request.getHeader("Authorization"));
		apiLoggerService.createApiLogger(apiLogger);
		return true;
	}
	
}
