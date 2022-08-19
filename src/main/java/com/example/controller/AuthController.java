package com.example.controller;

import java.util.Calendar;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AuthRequestDto;
import com.example.dto.AuthResponseDto;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ForgotPasswordDto;
import com.example.dto.ForgotPasswordRequestDto;
import com.example.dto.LoggerDto;
import com.example.dto.SuccessResponseDto;
import com.example.dto.UserDto;
import com.example.entities.UserEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.EmailService;
import com.example.service.ForgotPasswordServiceIntf;
import com.example.service.LoggerServiceInterface;
import com.example.service.UserService;
import com.example.serviceImpl.UserServiceImpl;
import com.example.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ForgotPasswordServiceIntf forgotPasswordServiceIntf;
	
	@Autowired
	private LoggerServiceInterface loggerServiceInterface;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUsers(@Valid @RequestBody UserDto userDto){
		
		try {
			
			String email=userDto.getEmail();
			Optional<UserEntity> dataBaseEmail = userRepository.findByEmailContainingIgnoreCase(email);
			if ((dataBaseEmail == null) || dataBaseEmail.isEmpty()) {
				userService.addUsers(userDto);
				
				return new ResponseEntity<>(new SuccessResponseDto("User Registered", "UserRegistered", null),
						HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(
					new ErrorResponseDto("User Email Id Already Exist", "UserEmailIdAlreadyExist"),
					HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponseDto("User Not Registered","UserNotRegistered"),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthRequestDto authenticationRequest) throws Exception, ResourceNotFoundException {

		try {

			UserEntity userEntity= userService.findByEmail(authenticationRequest.getEmail());

			if (!userServiceImpl.comparePassword(authenticationRequest.getPassword(), userEntity.getPassword())) {

				return new ResponseEntity<>(new ErrorResponseDto("Invalid Credential", "invalidCreds"), HttpStatus.UNAUTHORIZED);
			}
			
			System.out.println("DATA>>"+userEntity.getEmail());
			@SuppressWarnings("static-access")
			final String token = jwtTokenUtil.generateTokenOnLogin(userEntity.getEmail(), userEntity.getPassword());
		
			LoggerDto logger = new LoggerDto();
			logger.setToken(token);
			Calendar calender = Calendar.getInstance();
			calender.add(Calendar.MINUTE, 15);
			logger.setExpireAt(calender.getTime());
			loggerServiceInterface.createLogger(logger,userEntity);
			return new ResponseEntity(new SuccessResponseDto("Success", "success", new AuthResponseDto(token,userEntity.getEmail(),userEntity.getName(),userEntity.getId())), HttpStatus.OK);
			
		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

		}
 }
	
	
	
	
	@PostMapping("/forgot-pass")
	public ResponseEntity<?> forgotPass(@Valid @RequestBody ForgotPasswordRequestDto forgotPassBody, HttpServletRequest request) throws Exception {

			try {
					
				UserEntity userEntity = userService.findByEmail(forgotPassBody.getEmail());
			
				final String token = jwtTokenUtil.generateTokenOnForgotPass(userEntity.getEmail());
				
				
				final String url = "To confirm your account, please click here : " + "http://localhost:8088" + "/forgot-pass-confirm" + "?token=" + token;
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.MINUTE, 15);
				this.forgotPasswordServiceIntf.createForgotPasswordRequest(userEntity.getId(), token, calender.getTime());
				
				emailService.sendSimpleMessage(userEntity.getEmail(),"subject" , url);
				return new ResponseEntity<>(new SuccessResponseDto("Password reset link sent on Registerd Email", "passwordRestLinkMail", null), HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "candidateNotFound"), HttpStatus.NOT_FOUND);

			}

		}
	 @GetMapping("/logout")
		public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token, HttpServletRequest request) throws Exception {

			loggerServiceInterface.logoutUser(token);
			return new ResponseEntity<>(new ErrorResponseDto("Logout Successfully", "logoutSuccess"), HttpStatus.OK);

		}
}
