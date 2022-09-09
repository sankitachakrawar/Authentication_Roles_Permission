package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name="entities")
@Where(clause="is_active=true")
@SQLDelete(sql="UPDATE entities set is_active=false where id=?")
public class EntityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="entityName")
	private String entityName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_active")
	private Boolean is_Active=true;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(Boolean is_Active) {
		this.is_Active = is_Active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EntityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityEntity(Long id, String entityName, String description, Boolean is_Active, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.entityName = entityName;
		this.description = description;
		this.is_Active = is_Active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
}
