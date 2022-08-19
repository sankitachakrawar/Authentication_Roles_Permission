package com.example.service;

import org.springframework.data.domain.Page;

import com.example.dto.IPermissionDto;
import com.example.dto.PermissionRequestDto;
import com.example.entities.PermissionEntity;

public interface PermissionService {

	PermissionEntity addPermissions(PermissionRequestDto permissionsDto);
	
	PermissionEntity updatePermission(PermissionRequestDto permissionRequestDto , Long id);

	Page<IPermissionDto> getAllPermissions(String search, String from, String to);
	
	void deletePermission(Long id);

	
}
