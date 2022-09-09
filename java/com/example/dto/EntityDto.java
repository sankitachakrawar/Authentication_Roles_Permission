package com.example.dto;

public class EntityDto {

	private String entityName;
	
	private String description;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EntityDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityDto(String entityName, String description) {
		super();
		this.entityName = entityName;
		this.description = description;
	}
	
	
}
