package com.example.dto;


public class UserDataDto {

	private Long userId;

	private String name;

	private String email;
	
	
	public UserDataDto() {

	}

	public UserDataDto(Long userId, String name, String email) {

		super();
		this.userId = userId;
		this.name = name;
		this.email = email;

	}
	

	public Long getUserId() {

		return userId;

	}

	public void setUserId(Long userId) {

		this.userId = userId;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	@Override
	public String toString() {
		return "UserDataDto [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}

	

}
