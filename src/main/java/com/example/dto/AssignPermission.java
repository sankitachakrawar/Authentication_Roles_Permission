package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssignPermission {

	@NotBlank(message = "ActionName is Required*actionNameRequired")
	@NotEmpty(message = "ActionName is Required*actionNameRequired")
	@NotNull(message = "ActionName is Required*actionNameRequired")
	private String actionName;
	
	@NotBlank(message = "Role Name is Required*roleNamelRequired")
	@NotEmpty(message = "Role Name is Required*roleNameRequired")
	@NotNull(message = "Role Name is Required*roleNameRequired")
	private String roleName;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public AssignPermission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignPermission(
			@NotBlank(message = "ActionName is Required*actionNameRequired") @NotEmpty(message = "ActionName is Required*actionNameRequired") @NotNull(message = "ActionName is Required*actionNameRequired") String actionName,
			@NotBlank(message = "Role Name is Required*roleNamelRequired") @NotEmpty(message = "Role Name is Required*roleNameRequired") @NotNull(message = "Role Name is Required*roleNameRequired") String roleName) {
		super();
		this.actionName = actionName;
		this.roleName = roleName;
	}
	
	
}
