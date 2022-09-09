package com.example.dto;

import java.util.Date;


public class LoggerDto {


	public String token;

	public Long id;

	public Date createdAt;
	
	public Date expireAt;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public LoggerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoggerDto(String token, Long id, Date createdAt, Date expireAt) {
		super();
		this.token = token;
		this.id = id;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
	}


}
