package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.dto.EntityDto;
import com.example.dto.IEntityDto;
import com.example.entities.EntityEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.EntityRepository;
import com.example.service.EntityService;
import com.example.utils.PaginationUsingFromTo;

@Service
public class EntityServiceImpl implements EntityService{

	@Autowired
	private EntityRepository entityRepository;
	
	@Override
	public void addEntity(EntityDto entityDto) {
		EntityEntity entity=new EntityEntity();
		entity.setEntityName(entityDto.getEntityName());
		entity.setDescription(entityDto.getDescription());
		entityRepository.save(entity);
		
	}

	@Override
	public EntityEntity updateEntity(EntityEntity entity, Long id) {
		EntityEntity entityEntity =this.entityRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("entity", "id", id));
		
		entityEntity.setEntityName(entity.getEntityName());
		entityEntity.setDescription(entity.getDescription());
		EntityEntity entity2=this.entityRepository.save(entityEntity);
		return entity2;
	}

	@Override
	public Page<IEntityDto> getAllEntities(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return entityRepository.findByOrderById(paging, IEntityDto.class);
		} else {
			
			return entityRepository.findByEntityNameContainingIgnoreCaseOrderById(search, paging, IEntityDto.class);
			
		}	
		
	}

	@Override
	public void deleteEntity(Long id) {
		
		this.entityRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("entity", "id", id));
		this.entityRepository.deleteById(id);
		
	}

}
