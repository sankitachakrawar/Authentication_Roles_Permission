package com.example.service;

import com.example.entities.UserEntity;

public interface AuthService {

	public UserEntity loginUser(String email,String password)throws Exception;
	
}
