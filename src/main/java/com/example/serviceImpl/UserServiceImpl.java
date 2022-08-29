package com.example.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.dto.ChangePasswordDto;
import com.example.dto.ForgotPasswordDto;
import com.example.dto.IPermissionDto;
import com.example.dto.IUserDto;
import com.example.dto.RoleIdListDto;
import com.example.dto.UserDataDto;
import com.example.dto.UserDto;
import com.example.entities.Forgot_password_request;
import com.example.entities.UserEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.ForgotPasswordRequestRepository;
import com.example.repository.RolePermissionRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.UserService;
import com.example.utils.JwtTokenUtil;
import com.example.utils.PaginationUsingFromTo;


@Service
public class UserServiceImpl implements UserService{

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ForgotPasswordRequestRepository forgotPasswordRequestRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	
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
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
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
	
	@Override
	public void forgotPasswordConfirm( String token,@Valid ForgotPasswordDto userBody, HttpServletRequest request) {
		
		
		DecodedJWT jwt = JWT.decode(token); 
		Date CurrentDate = new Date(System.currentTimeMillis());
		
		if (CurrentDate.before(jwt.getExpiresAt())) {

			if (userBody.getPassword().equals(userBody.getConfirmpassword())) {
				
				String email = null;
				String jwtToken = null; 
				jwtToken = userBody.getToken();
				email = jwtTokenUtil.getEmailFromToken(jwtToken); 
				
				UserEntity userEntity = userRepository.getUserByEmail(email);

				userEntity.setPassword(bcryptEncoder.encode(userBody.getPassword()));
				userRepository.save(userEntity);

			} else {

				throw new ResourceNotFoundException("password and confirm password must be a same");
			}

		} else {

			Forgot_password_request forgot_password_request = forgotPasswordRequestRepository
					.getByTokenOrderByIdDesc(token).orElseThrow(() -> new ResourceNotFoundException("Invalid Request"));
			forgot_password_request.setIsActive(false);
			throw new ResourceNotFoundException("Reset the password time out");

		}
		
	}
	
	
	@Override
	public List<IPermissionDto> getUserPermission(Long userId) throws IOException {

		ArrayList<RoleIdListDto> roleIds = userRoleRepository.findByPkUserId(userId, RoleIdListDto.class);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++) {

			roles.add(roleIds.get(i).getPkRoleId());

		}

		return rolePermissionRepository.findByPkRoleIdIn(roles, IPermissionDto.class);

	}
	
	@Override
	public void changePassword(Long id, ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		final String requestTokenHeader = request.getHeader("Authorization");
		String email = null;
		String jwtToken = null;
		
		jwtToken = requestTokenHeader.substring(7);
		email = jwtTokenUtil.getEmailFromToken(jwtToken);
		System.out.println("object>>"+email);
		
		UserDataDto userdata = new UserDataDto();
		userdata.setEmail(email.toString());
		System.out.println("data>>>"+userdata);
		
		if (userdata.getEmail().equals(user.getEmail()) ) {
			System.out.println("dto>>"+userdata.getEmail()+"entity>>"+user.getEmail());
			if (!bcryptEncoder.matches(userBody.getNewPassword(), user.getPassword())) {
 
				if (bcryptEncoder.matches(userBody.getPassword(), user.getPassword())) {

					user.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
					if (userBody.getNewPassword().equals(userBody.getConfPassword())) {
						user.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
						userRepository.save(user);
					} else {
						throw new ResourceNotFoundException("new password and confirm password must be same");
					}

				} else {

					throw new ResourceNotFoundException("Please enter old password correct");
				}

			} else {
				throw new ResourceNotFoundException("password must be differ from old password");
			}

		} else {
			throw new ResourceNotFoundException("Access Denied");
		}
		
		
	}

	
	@Override
	public UserEntity getUsers(@PathVariable Long id) {
		
		UserEntity entity= this.userRepository.getUser(id);
		return entity;
		
	}
}
