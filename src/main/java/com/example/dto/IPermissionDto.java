package com.example.dto;

public interface IPermissionDto {

	public Long getId();
	
	public String getActionName();
	
	public String getDescription();
	
	public String getMethod();
	
	public String getBaseUrl();
	
	public String getPath();
}
