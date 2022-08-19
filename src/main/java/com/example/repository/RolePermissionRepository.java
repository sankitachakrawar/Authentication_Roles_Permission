package com.example.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dto.IPermissionIdList;
import com.example.entities.RolePermissionEntity;



public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long>{

	List<IPermissionIdList> findPkPermissionByPkRoleIdIn(ArrayList<Long> roleIds, Class<IPermissionIdList> IPermissionIdList);

	Page<RolePermissionEntity> findByOrderByPk(Pageable paging, Class<RolePermissionEntity> class1);

	Page<RolePermissionEntity> findByIsActiveContainingIgnoreCaseOrderByPk(String search, Pageable paging,
			Class<RolePermissionEntity> class1);

}
