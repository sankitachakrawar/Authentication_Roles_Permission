package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.ErrorResponseDto;
import com.example.dto.IUserDto;
import com.example.dto.ListResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.dto.UserDto;
import com.example.entities.UserEntity;
import com.example.service.UserService;



@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	

	@SuppressWarnings("unchecked")
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
		
		Page<IUserDto> user = userService.getAllUsers(search, pageNo, size);
		if (user.getTotalElements() != 0) {
			return new ResponseEntity(new SuccessResponseDto("Success", "success",
					new ListResponseDto(user.getContent(), user.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
	
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserEntity user,@PathVariable Long id){
		
		this.userService.updateUser(user, id);
		
		return new ResponseEntity<>("user updated successfully",HttpStatus.OK);	
		
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable Long id){
		userService.deleteUsers(id);
		return new  ResponseEntity<>("User deleted sucesssfully!!",HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserEntity> getSingleUser(@PathVariable Long id){
		
		return ResponseEntity.ok(this.userService.getUserById(id));
		
	}
}
