package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.IEntityDto;
import com.example.dto.IPermissionDto;
import com.example.dto.PermissionDto;
import com.example.dto.PermissionRequestDto;
import com.example.entities.PermissionEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.EntityRepository;
import com.example.repository.PermissionRepository;
import com.example.service.PermissionService;
import com.example.utils.PaginationUsingFromTo;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Override
	public PermissionEntity addPermissions(PermissionRequestDto permissionsDto) {
		PermissionEntity permissionEntity=new PermissionEntity();
		permissionEntity.setActionName(permissionsDto.getActionName());
		permissionEntity.setDescription(permissionsDto.getDescription());
		permissionEntity.setMethod(permissionsDto.getMethod());
		permissionEntity.setBaseUrl(permissionsDto.getBaseUrl());
		permissionEntity.setPath(permissionsDto.getPath());
		permissionEntity.setEntityId(entityRepository.getById(permissionsDto.getEntityId()));
		PermissionEntity permissionEntity2=permissionRepository.save(permissionEntity);
		return permissionEntity2;
	}

	@Override
	public PermissionEntity updatePermission(PermissionRequestDto permissionEntity, Long id) {
		PermissionEntity permissionEntity2=this.permissionRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("permission", "id", id));
		permissionEntity2.setActionName(permissionEntity.getActionName());
		permissionEntity2.setDescription(permissionEntity.getDescription());
		permissionEntity2.setMethod(permissionEntity.getMethod());
		permissionEntity2.setBaseUrl(permissionEntity.getBaseUrl());
		permissionEntity2.setPath(permissionEntity.getPath());
		PermissionEntity entity=permissionRepository.save(permissionEntity2);
		
		return entity;
	}

	@Override
	public Page<IPermissionDto> getAllPermissions(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return permissionRepository.findByOrderById(paging, IPermissionDto.class);
		} else {
			
			return permissionRepository.findByActionNameContainingIgnoreCaseOrderById(search, paging, IPermissionDto.class);
			
		}	
	}

	@Override
	public void deletePermission(Long id) {
	
	this.permissionRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("permission", "id", id));
		this.permissionRepository.deleteById(id);
	}

}
