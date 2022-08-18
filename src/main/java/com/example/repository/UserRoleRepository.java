package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{

	Page<UserRoleEntity> findByOrderByPk(Pageable paging, Class<UserRoleEntity> class1);

	Page<UserRoleEntity> findByIsActiveContainingIgnoreCaseOrderByPk(String search, Pageable paging,
			Class<UserRoleEntity> class1);

}
