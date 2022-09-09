package com.example.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



public class AssignRole {
	@NotBlank(message = "Email is Required*emailRequired")
	@NotEmpty(message = "Email is Required*emailRequired")
	@NotNull(message = "Email is Required*emailRequired")
	private String email;
	
	@NotBlank(message = "Role Name is Required*roleNamelRequired")
	@NotEmpty(message = "Role Name is Required*roleNameRequired")
	@NotNull(message = "Role Name is Required*roleNameRequired")
	private String roleName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public AssignRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignRole(
			@NotBlank(message = "Email is Required*emailRequired") @NotEmpty(message = "Email is Required*emailRequired") @NotNull(message = "Email is Required*emailRequired") String email,
			@NotBlank(message = "Role Name is Required*roleNamelRequired") @NotEmpty(message = "Role Name is Required*roleNameRequired") @NotNull(message = "Role Name is Required*roleNameRequired") String roleName) {
		super();
		this.email = email;
		this.roleName = roleName;
	}
	
	
	
}