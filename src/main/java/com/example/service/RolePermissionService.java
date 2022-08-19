package com.example.service;

import org.springframework.data.domain.Page;

import com.example.dto.AssignPermission;
import com.example.entities.RolePermissionEntity;


public interface RolePermissionService {

	void addPermissionToRole(AssignPermission assignPermission);
	
	Page<RolePermissionEntity> getAllRolesPermissions(String search, String from, String to);
}
