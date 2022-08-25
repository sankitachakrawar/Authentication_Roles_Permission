package com.example.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ChangePasswordDto;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ForgotPasswordDto;
import com.example.dto.IUserDto;
import com.example.dto.ListResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.dto.UserDto;
import com.example.entities.UserEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('getAllUsers')")
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
	
	@PreAuthorize("hasRole('updateUser')")
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserEntity user,@PathVariable Long id){
		
		this.userService.updateUser(user, id);
		
		return new ResponseEntity<>("user updated successfully",HttpStatus.OK);	
		
	}
	
	@PreAuthorize("hasRole('deleteUsers')")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable Long id){
		userService.deleteUsers(id);
		return new  ResponseEntity<>("User deleted sucesssfully!!",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('getSingleUser')")
	@GetMapping("/user/{id}")
	public ResponseEntity<UserEntity> getSingleUser(@PathVariable Long id){
		
		return ResponseEntity.ok(this.userService.getUserById(id));
		
	}
	
	 @PutMapping("/forgot-pass-confirm")
	  public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto,HttpServletRequest request) throws ResourceNotFoundException {
	  
	  try {	 
		  userService.forgotPasswordConfirm(forgotPasswordDto.getToken(), forgotPasswordDto, request);
		  System.out.println("password>>"+forgotPasswordDto);
	  return new ResponseEntity<>("password Updated",HttpStatus.OK);
	  
	  } catch (ResourceNotFoundException e) {
	  
	  return new ResponseEntity<>( "Access Denied", HttpStatus.BAD_GATEWAY);
	  
	 }
	  }
	 
	 //@PreAuthorize("hasRole('editUser')")
		@PutMapping("/changePass/{id}")
		public ResponseEntity<?> changePasswords(@PathVariable(value = "id") Long userId,
				@Valid @RequestBody ChangePasswordDto userBody, HttpServletRequest request)
				throws ResourceNotFoundException {

			try {

				userService.changePassword(userId, userBody, request);
				return new ResponseEntity<>(new SuccessResponseDto("password Updated", "password Updated succefully", null),
						HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Access Denied"), HttpStatus.BAD_GATEWAY);

			}

		}
	 
}
