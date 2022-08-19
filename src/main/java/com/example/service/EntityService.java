package com.example.service;

import org.springframework.data.domain.Page;
import com.example.dto.EntityDto;
import com.example.dto.IEntityDto;
import com.example.entities.EntityEntity;

public interface EntityService {
	
	public void addEntity(EntityDto entityDto);
	
	EntityEntity updateEntity(EntityEntity entity , Long id);

	Page<IEntityDto> getAllEntities(String search, String from, String to);
	
	void deleteEntity(Long id);
}
