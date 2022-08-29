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
@Table(name = "logger")
//@Where(clause = "is_active = true")
//@SQLDelete(sql="UPDATE logger SET is_active=false WHERE id=?")
public class LoggerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loggerid;

	@OneToOne(fetch = FetchType.LAZY)   
	@JoinColumn(name = "user_id")
	private UserEntity id;

	@Column(name = "token", length = 512)
	private String token;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private Date createdAt;
	
	private Date expireAt;

	@Column(name = "is_active")
	private Boolean isActive = true;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LoggerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoggerEntity(Long loggerid, UserEntity id, String token, Date createdAt, Date expireAt, Boolean isActive) {
		super();
		this.loggerid = loggerid;
		this.id = id;
		this.token = token;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "LoggerEntity [loggerid=" + loggerid + ", id=" + id + ", token=" + token + ", createdAt=" + createdAt
				+ ", expireAt=" + expireAt + ", isActive=" + isActive + "]";
	}
	
	
}
