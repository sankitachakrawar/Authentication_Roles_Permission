package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "otpLogger")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE otpLogger SET is_active=false WHERE id=?")
public class OtpLogger implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loggerid;

	@OneToOne(fetch = FetchType.LAZY)   
	@JoinColumn(name = "user_id")
	private UserEntity id;

	@Column(name = "otp", length = 512)
	private String otp;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="expireAt")
	private Date expireAt;

	@Column(name = "is_active")
	private boolean isActive = true;

	public OtpLogger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpLogger(Long loggerid, UserEntity id, String otp, Date createdAt, Date expireAt, boolean isActive) {
		super();
		this.loggerid = loggerid;
		this.id = id;
		this.otp = otp;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
		this.isActive = isActive;
	}

	public Long getLoggerid() {
		return loggerid;
	}

	public void setLoggerid(Long loggerid) {
		this.loggerid = loggerid;
	}

	public UserEntity getId() {
		return id;
	}

	public void setId(UserEntity id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
