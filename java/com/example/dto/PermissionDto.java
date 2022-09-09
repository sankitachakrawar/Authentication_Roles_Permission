package com.example.dto;

public class PermissionDto {

private Long id;
	
	private String actionName;
	
	private String description;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PermissionDto(Long id, String actionName, String description) {
		super();
		this.id = id;
		this.actionName = actionName;
		this.description = description;
	}


	
	
	
}
