package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.dto.IUserDto;
import com.example.dto.UserDto;
import com.example.entities.UserEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.utils.PaginationUsingFromTo;

@Service
public class UserServiceImpl implements UserService{

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Override
	public void addUsers(UserDto userDto) {
		
		UserEntity userEntity=new UserEntity();
		userEntity.setName(userDto.getName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(userEntity);
		
	}

	@Override
	public Page<IUserDto> getAllUsers(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return userRepository.findByOrderById(paging, IUserDto.class);
		} else {
			
			return userRepository.findByNameContainingIgnoreCaseOrderById(search, paging, IUserDto.class);
			
		}		
	}

	@Override
	public UserEntity updateUser(UserEntity user, Long id) {
		UserEntity userEntity =this.userRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("users", "id", id));
		
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setAddress(user.getAddress());
		userEntity.setPassword(user.getPassword());
		userEntity.setUsername(user.getUsername());
		UserEntity entity=this.userRepository.save(userEntity);
		return entity;
	}

	@Override
	public void deleteUsers(Long id) {
	
		this.userRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("users", "id", id));
		this.userRepository.deleteById(id);
		
	}

	@Override
	public UserEntity getUserById(Long id) {
		UserEntity userEntity  = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("users", "id", id));
		
		return userEntity;
	}
	 
	@Override
	public Boolean comparePassword(String password, String hashPassword) {

		return bcryptEncoder.matches(password, hashPassword);

	}

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		return userEntity;
	}
}
