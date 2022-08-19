package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.ErrorResponseDto;
import com.example.dto.IPermissionDto;
import com.example.dto.ListResponseDto;
import com.example.dto.PermissionDto;
import com.example.dto.PermissionRequestDto;
import com.example.dto.SuccessResponseDto;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.service.PermissionService;

@RestController
@RequestMapping("/api")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping("/permission")
	public ResponseEntity<?> addPermissions(@RequestBody PermissionRequestDto permissionRequestDto){
		permissionService.addPermissions(permissionRequestDto);
		return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null),HttpStatus.OK);
	}
	
	@PutMapping("/permission/{id}")
	public ResponseEntity<?> updatepermissions(@RequestBody PermissionRequestDto permissionRequestDto,@PathVariable Long id){
		try {

			permissionService.updatePermission(permissionRequestDto, id);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "permissionNotFound"), HttpStatus.NOT_FOUND);

		}
	}
	
	@DeleteMapping("/permission/{id}")
	public ResponseEntity<?> deletePermission(@PathVariable Long id){
		this.permissionService.deletePermission(id);
		return new ResponseEntity<>("Permission Deleted",HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/permission")
	public ResponseEntity<List<PermissionDto>> getAllUsers(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
		
		Page<IPermissionDto> permission = permissionService.getAllPermissions(search, pageNo, size);
		if (permission.getTotalElements() != 0) {
			return new ResponseEntity(new SuccessResponseDto("Success", "success",
					new ListResponseDto(permission.getContent(), permission.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
	
	}
}
