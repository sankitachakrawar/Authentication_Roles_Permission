package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="file_upload")

public class FileUploadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="fileName")
	private String fileName;
	
	@Column(name="size")
	private Long size;
	
	@Column(name="type")
	private String type;
	
	@Column(name="originalName")
	private String originalName;
	
	@Column(name="created_At")
	@CreationTimestamp
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public FileUploadEntity() {
		super();
		
	}

	public FileUploadEntity(Long id, String fileName, Long size, String type, String originalName, Date createdAt) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.size = size;
		this.type = type;
		this.originalName = originalName;
		this.createdAt = createdAt;
	}

	
	
	
	
	
}
