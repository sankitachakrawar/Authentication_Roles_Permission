package com.example.dto;

public class RoleUserDto {

	private Long id;
	private String name;
	private String roleName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public RoleUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleUserDto(Long id, String name, String roleName) {
		super();
		this.id = id;
		this.name = name;
		this.roleName = roleName;
	}
	
	
}
