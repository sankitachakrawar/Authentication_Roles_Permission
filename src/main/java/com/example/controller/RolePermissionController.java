package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AssignPermission;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ListResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.entities.RolePermissionEntity;
import com.example.entities.UserRoleEntity;
import com.example.service.RolePermissionService;

@RestController
@RequestMapping("/api")
public class RolePermissionController {

	@Autowired
	private RolePermissionService rolePermissionService;
	
	@PreAuthorize("hasRole('assignPermissionToRole')")
	@PostMapping("/assignPermission")
	public ResponseEntity<?> assignPermissionToRole(@RequestBody AssignPermission assignPermission,HttpServletRequest request){
		
		try {
			rolePermissionService.addPermissionToRole(assignPermission);
			return new ResponseEntity<>(new SuccessResponseDto("Permission Assign To Role","PermissionAssignToRole",null),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Permission not assign to Role","PermissionNotAssignToRole"),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PreAuthorize("hasRole('getAllPermissions')")
	@GetMapping("/rolePermission")
	public ResponseEntity<?> getAllRolesPermissions(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
		
		Page<RolePermissionEntity> rolePermissionEntity=rolePermissionService.getAllRolesPermissions(search, pageNo, size);
		System.out.println("role>>"+rolePermissionEntity);
		if (rolePermissionEntity.getTotalElements() != 0) {
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
					new ListResponseDto(rolePermissionEntity.getContent(), rolePermissionEntity.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
	}
}
