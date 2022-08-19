package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.EntityDto;
import com.example.dto.ErrorResponseDto;
import com.example.dto.IEntityDto;
import com.example.dto.ListResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.entities.EntityEntity;
import com.example.service.EntityService;


@RestController
@RequestMapping("/api")
public class EntityController {

	@Autowired
	private EntityService entityService;
	
	@PostMapping("/entity")
	public ResponseEntity<?> addEntity(@RequestBody EntityDto entityDto){
		
		entityService.addEntity(entityDto);
		return new ResponseEntity<>(new SuccessResponseDto("Entity Added", "EntityAdded", null),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/entity/{id}")
	public ResponseEntity<?> updateEntity(@RequestBody EntityEntity entityEntity, @PathVariable Long id){
		
		this.entityService.updateEntity(entityEntity, id);
		return new ResponseEntity<>("Entity Updated",HttpStatus.OK);
		
	}
	
	@DeleteMapping("/entity/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id){
		this.entityService.deleteEntity(id);
		return new ResponseEntity<>("Entity Deleted",HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/entity")
	public ResponseEntity<List<EntityDto>> getAllEntity(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
			
		Page<IEntityDto> entity = entityService.getAllEntities(search, pageNo, size);
		if (entity.getTotalElements() != 0) {
			return new ResponseEntity(new SuccessResponseDto("Success", "success",
					new ListResponseDto(entity.getContent(), entity.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
		
	}
	
}
