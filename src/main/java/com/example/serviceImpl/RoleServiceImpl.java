package com.example.serviceImpl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.IPermissionIdList;
import com.example.dto.IRoleDto;
import com.example.dto.RoleDto;
import com.example.dto.RoleIdListDto;
import com.example.entities.RoleEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.RolePermissionRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.RoleService;
import com.example.utils.PaginationUsingFromTo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public void addRole(RoleDto roleDto) {
		
		RoleEntity roleEntity=new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
		roleRepository.save(roleEntity);
		
	}

	@Override
	public Page<IRoleDto> getAllRoles(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return roleRepository.findByOrderById(paging, IRoleDto.class);
		} else {
			
			return roleRepository.findByRoleNameContainingIgnoreCaseOrderById(search, paging, IRoleDto.class);
			
		}
	}

	@Override
	public RoleEntity updateRole(RoleEntity role, Long id) {
		RoleEntity roleEntity =this.roleRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("role", "id", id));
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setDescription(role.getDescription());
		RoleEntity entity=roleRepository.save(roleEntity);
		return entity;
	}

	@Override
	public void deleteRoles(Long id) {
		
		this.roleRepository.findById(id).
		orElseThrow(() -> new ResourceNotFoundException("role", "id", id));

		this.roleRepository.deleteById(id);
	}

	@Override
	public ArrayList<String> getPermissionByUserId(Long id) {
		
		ArrayList<RoleIdListDto> roleIds=userRoleRepository.findByPkUserId(id, RoleIdListDto.class);
		ArrayList<Long> roles=new ArrayList<>();
		
		for (int i = 0; i < roleIds.size(); i++) {

			roles.add(roleIds.get(i).getPkRoleId());

		}
		List<IPermissionIdList> rolesPermission=rolePermissionRepository.findPkPermissionByPkRoleIdIn(roles, IPermissionIdList.class);
		ArrayList<String> permissions=new ArrayList<>();
		
		for (IPermissionIdList element : rolesPermission) {

			permissions.add(element.getPkPermissionActionName());

		}

		return permissions;
	}


	
}
