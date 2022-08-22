package com.example.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dto.RoleIdListDto;
import com.example.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{

	Page<UserRoleEntity> findByOrderByPk(Pageable paging, Class<UserRoleEntity> class1);

	Page<UserRoleEntity> findByIsActiveContainingIgnoreCaseOrderByPk(String search, Pageable paging,
			Class<UserRoleEntity> class1);

	ArrayList<RoleIdListDto> findByPkUserId(Long userId, Class<RoleIdListDto> RoleIdListDto);

	//@Query(value="select * from user_role u where u.user_id=:user_id",nativeQuery = true)
	//ArrayList<UserRoleEntity> findByUserId(@Param("user_id") Long userId);

}
