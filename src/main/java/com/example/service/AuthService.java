package com.example.service;


import java.util.Optional;

import com.example.entities.SPRequestEntity;
import com.example.entities.UserEntity;

public interface AuthService {

	public UserEntity loginUser(String email,String password)throws Exception;
	
	public Optional<SPRequestEntity> getUserById(Long id);
	
}
