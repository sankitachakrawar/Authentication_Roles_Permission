package com.example.service;
import org.springframework.data.domain.Page;
import com.example.dto.IUserDto;
import com.example.dto.UserDto;
import com.example.entities.UserEntity;

public interface UserService {

	public void addUsers(UserDto userDto);
	
	Page<IUserDto> getAllUsers(String search, String from, String to);
	
	UserEntity updateUser(UserEntity user, Long id);
	
	void deleteUsers(Long id);
	
	UserEntity getUserById(Long id);

	public UserEntity findByEmail(String email);

	Boolean comparePassword(String password, String hashPassword);
}
