package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.IPermissionDto;
import com.example.entities.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>{

	Page<IPermissionDto> findByOrderById(Pageable paging, Class<IPermissionDto> class1);

	Page<IPermissionDto> findByActionNameContainingIgnoreCaseOrderById(String search, Pageable paging,
			Class<IPermissionDto> class1);

	PermissionEntity findByActionNameContainingIgnoreCase(String actionName);

}
