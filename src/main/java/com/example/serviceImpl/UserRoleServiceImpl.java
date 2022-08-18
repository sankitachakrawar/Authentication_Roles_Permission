package com.example.serviceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.dto.AssignRole;
import com.example.entities.RoleEntity;
import com.example.entities.UserEntity;
import com.example.entities.UserRoleEntity;
import com.example.entities.UserRoleId;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.UserRoleService;
import com.example.utils.PaginationUsingFromTo;


@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public void addRoleToUser(AssignRole assignRole) {
		
		try {
			ArrayList<UserRoleEntity> roles=new ArrayList<>();
			
			UserEntity user=userRepository.findByEmail(assignRole.getEmail());
			
			RoleEntity role=roleRepository.findByRoleNameContainingIgnoreCase(assignRole.getRoleName());
			
			UserRoleEntity userRoleEntity=new UserRoleEntity();
			
			UserRoleId userRoleId=new UserRoleId(user, role);
			
			userRoleEntity.setPk(userRoleId);
			
			roles.add(userRoleEntity);
			
			userRoleRepository.saveAll(roles);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Page<UserRoleEntity> getAllUserRoles(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return userRoleRepository.findByOrderByPk(paging, UserRoleEntity.class);
		} else {
			
			return userRoleRepository.findByIsActiveContainingIgnoreCaseOrderByPk(search, paging, UserRoleEntity.class);
			
				
		}
	}

	

	
}
