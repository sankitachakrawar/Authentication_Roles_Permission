package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.IEntityDto;
import com.example.entities.EntityEntity;

public interface EntityRepository extends JpaRepository<EntityEntity, Long> {

	Page<IEntityDto> findByOrderById(Pageable paging, Class<IEntityDto> class1);

	Page<IEntityDto> findByEntityNameContainingIgnoreCaseOrderById(String search, Pageable paging,
			Class<IEntityDto> class1);

}
