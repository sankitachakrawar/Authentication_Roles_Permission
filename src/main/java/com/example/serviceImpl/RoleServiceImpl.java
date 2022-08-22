package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.dto.EntityDto;
import com.example.dto.EntityPermissionDto;
import com.example.dto.IPermissionDto;
import com.example.dto.IPermissionIdList;
import com.example.dto.IRoleDto;
import com.example.dto.RoleDto;
import com.example.dto.RoleIdListDto;
import com.example.dto.RolePermissionDto;
import com.example.dto.RoleUserDto;
import com.example.dto.UserDto;
import com.example.entities.EntityEntity;
import com.example.entities.PermissionEntity;
import com.example.entities.RoleEntity;
import com.example.entities.UserEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.EntityRepository;
import com.example.repository.PermissionRepository;
import com.example.repository.RolePermissionRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
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
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
	public ArrayList<String> getPermissionByUserId(Long userId) {
		
		ArrayList<RoleIdListDto> roleIds=userRoleRepository.findByPkUserId(userId,RoleIdListDto.class);
		System.out.println("roleIds>>  "+roleIds);
		ArrayList<Long> role=new ArrayList<Long>();
		System.out.println("roles>> "+role);
		
		for (int i = 0; i < roleIds.size(); i++) {

			role.add(roleIds.get(i).getPkRoleId());

		}
		List<IPermissionIdList> rolesPermission=rolePermissionRepository.findPkPermissionByPkRoleIdIn(role, IPermissionIdList.class);
		System.out.println("rolesPermission>> "+rolesPermission);
		ArrayList<String> permissions=new ArrayList<String>();
		System.out.println("permissions>>" +permissions);
		
		for (IPermissionIdList element : rolesPermission) {

			permissions.add(element.getPkPermissionActionName());

		}

		return permissions;
	}

	@Override
	public RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException {
		
		RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		System.out.println("roleEntity>> "+roleEntity);
		List<EntityEntity> entities = entityRepository.findAll();
		System.out.println("entities>>  "+entities);
		List<PermissionEntity> permissions = permissionRepository.findAll();
		System.out.println("permissions>>  " +permissions);
		ArrayList<IPermissionDto> rolesPermission = rolePermissionRepository.findByPkRoleId(id, IPermissionDto.class);
		System.out.println("rolesPermission>>  "+rolesPermission);
		ArrayList<EntityPermissionDto> entityPermission = new ArrayList<>();
		System.out.println("entityPermission>>  "+entityPermission);

		for (PermissionEntity permission : permissions) {

			EntityPermissionDto singleEntityPermisson = new EntityPermissionDto();
			singleEntityPermisson.setActionName(permission.getDescription());
			singleEntityPermisson.setId(permission.getId());
			singleEntityPermisson.setEntityId(permission.getEntityId().getId());
			singleEntityPermisson.setIsSelected(false);

			for (IPermissionDto element : rolesPermission) {

				if (permission.getId() == element.getPkPermissionId()) {

					singleEntityPermisson.setIsSelected(true);
					break;

				}

			}

			entityPermission.add(singleEntityPermisson);

		}

		ArrayList<EntityDto> entityDto = new ArrayList<>();

		for (EntityEntity element : entities) {

			Boolean isEntityEnabled = false;
			ArrayList<EntityPermissionDto> entityPermission1 = new ArrayList<>();

			for (int j = 0; j < entityPermission.size(); j++) {

				if (element.getId() == entityPermission.get(j).getEntityId()) {

					if (entityPermission.get(j).getIsSelected()) {

						isEntityEnabled = true;

					}

					entityPermission1.add(entityPermission.get(j));

				}

			}

			EntityDto singleEntityDto = new EntityDto();
			singleEntityDto.setEntityName(element.getEntityName());
			entityDto.add(singleEntityDto);

		}

		RolePermissionDto rolePermissionDto = new RolePermissionDto();
		rolePermissionDto.setId(roleEntity.getId());
		rolePermissionDto.setRoleName(roleEntity.getRoleName());
		rolePermissionDto.setDescription(roleEntity.getDescription());
		rolePermissionDto.setEntity(entityDto);
		return rolePermissionDto;
	}

	@Override
	public RoleUserDto getRoleAndUserById(Long id) throws ResourceNotFoundException {
		RoleEntity roleEntity=roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		List<UserEntity> user=userRepository.findAll();
		
		for (UserEntity users : user) {
			UserDto dto=new UserDto();
			dto.setName(users.getName());
			
			userRepository.findAll();

	}
		RoleUserDto roleuserDto = new RoleUserDto();
		roleuserDto.setId(roleEntity.getId());
		roleuserDto.setRoleName(roleEntity.getRoleName());

		return roleuserDto;
	}


	
}
