package com.example.service;

import org.springframework.data.domain.Page;


import com.example.dto.AssignRole;
import com.example.entities.UserRoleEntity;

public interface UserRoleService {

	void addRoleToUser(AssignRole assignRole);
	
	Page<UserRoleEntity> getAllUserRoles(String search, String from, String to);
}
