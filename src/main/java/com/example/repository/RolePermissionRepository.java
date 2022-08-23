package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.IPermissionDto;
import com.example.dto.IPermissionIdList;
import com.example.entities.RolePermissionEntity;


@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long>{

	List<IPermissionIdList> findPkPermissionByPkRoleIdIn(ArrayList<Long> roleIds, Class<IPermissionIdList> IPermissionIdList);

	Page<RolePermissionEntity> findByOrderByPk(Pageable paging, Class<RolePermissionEntity> class1);

	Page<RolePermissionEntity> findByIsActiveContainingIgnoreCaseOrderByPk(String search, Pageable paging,
			Class<RolePermissionEntity> class1);

	//ArrayList<IPermissionDto> findByPkRoleId(Long id, Class<IPermissionDto> class1);

	ArrayList<IPermissionDto> findByPkRoleId(Long roleId, Class<IPermissionDto> IPermissionDto);

	List<IPermissionDto> findByPkRoleIdIn(ArrayList<Long> roles, Class<IPermissionDto> class1);
}
