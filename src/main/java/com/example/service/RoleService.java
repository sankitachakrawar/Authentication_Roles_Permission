package com.example.service;

import java.util.ArrayList;


import org.springframework.data.domain.Page;
import com.example.dto.IRoleDto;
import com.example.dto.RoleDto;
import com.example.dto.RolePermissionDto;
import com.example.dto.RoleUserDto;
import com.example.entities.RoleEntity;
import com.example.exceptionHandling.ResourceNotFoundException;

public interface RoleService {

	public void addRole(RoleDto roleDto);
	
	Page<IRoleDto> getAllRoles(String search, String from, String to);
	
	RoleEntity updateRole(RoleEntity role, Long id);
	
	void deleteRoles(Long id);
	
	ArrayList<String> getPermissionByUserId(Long id);
	
	RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException;

	RoleUserDto getRoleAndUserById(Long id) throws ResourceNotFoundException;
}
