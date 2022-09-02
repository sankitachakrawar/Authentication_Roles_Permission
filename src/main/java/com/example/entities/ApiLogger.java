package com.example.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="api_logger")
public class ApiLogger implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="url")
	private String url;
	
	@Column(name="method")
	private String method;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private Date createdAt;

	@Column(name="token")
	private String token;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public ApiLogger() {
		super();
		
	}

	public ApiLogger(Long id, String url, String method, Date createdAt, String token) {
		super();
		this.id = id;
		this.url = url;
		this.method = method;
		this.createdAt = createdAt;
		this.token = token;
	}

	
	
}
