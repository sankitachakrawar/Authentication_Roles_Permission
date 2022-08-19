package com.example.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.dto.ForgotPasswordDto;
import com.example.entities.UserEntity;

public interface AuthService {

	public UserEntity loginUser(String email,String password)throws Exception;
	
	
}
