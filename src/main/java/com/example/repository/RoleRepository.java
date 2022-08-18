package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dto.IRoleDto;
import com.example.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	Page<IRoleDto> findByOrderById(Pageable paging, Class<IRoleDto> class1);

	Page<IRoleDto> findByRoleNameContainingIgnoreCaseOrderById(String search, Pageable paging, Class<IRoleDto> class1);

	RoleEntity findByRoleNameContainingIgnoreCase(String roleName);

}
