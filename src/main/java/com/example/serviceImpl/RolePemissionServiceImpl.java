package com.example.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.AssignPermission;
import com.example.entities.PermissionEntity;
import com.example.entities.RoleEntity;
import com.example.entities.RolePermissionEntity;
import com.example.entities.RolePermissionId;
import com.example.entities.UserRoleEntity;
import com.example.repository.PermissionRepository;
import com.example.repository.RolePermissionRepository;
import com.example.repository.RoleRepository;
import com.example.service.RolePermissionService;
import com.example.utils.PaginationUsingFromTo;

@Service
public class RolePemissionServiceImpl implements RolePermissionService{

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public void addPermissionToRole(AssignPermission assignPermission) {

		try {
		ArrayList<RolePermissionEntity> rolePermissionEntities=new ArrayList<>();
		PermissionEntity permissionEntity=permissionRepository.findByActionNameContainingIgnoreCase(assignPermission.getActionName());
		System.out.println("permission>>"+permissionEntity);
		
		RoleEntity roleEntity=roleRepository.findByRoleNameContainingIgnoreCase(assignPermission.getRoleName());
		System.out.println("roles>>"+roleEntity);
		
		RolePermissionEntity entity=new RolePermissionEntity();
		
		RolePermissionId rolePermissionId=new RolePermissionId(roleEntity, permissionEntity);
		
		entity.setPk(rolePermissionId);
		
		rolePermissionEntities.add(entity);
		
		rolePermissionRepository.saveAll(rolePermissionEntities);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Page<RolePermissionEntity> getAllRolesPermissions(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return rolePermissionRepository.findByOrderByPk(paging, RolePermissionEntity.class);
		} else {
			
			return rolePermissionRepository.findByIsActiveContainingIgnoreCaseOrderByPk(search, paging, RolePermissionEntity.class);
			
				
		}
	}

}
