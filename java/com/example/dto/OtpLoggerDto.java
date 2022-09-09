package com.example.dto;

import java.util.Date;

public class OtpLoggerDto {

	public String otp;

	public Long id;

	public Date createdAt;
	
	public Date expireAt;

	public OtpLoggerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpLoggerDto(String otp, Long id, Date createdAt, Date expireAt) {
		super();
		this.otp = otp;
		this.id = id;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
	
	
}
