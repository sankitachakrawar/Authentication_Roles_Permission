package com.example.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="sp_request")
public class SPRequestEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",unique = true)
	private UserEntity userId;
	
	@Column(name = "entity_id")
	private ArrayList<Long> EntityId;
	
	@Column(name = "permission_id")
	private ArrayList<Long> PermissionId;
	
	@Column(name = "role_id")
	private ArrayList<Long> RoleId;
	

	@Column(name = "path")
	private String path;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private UserEntity createdBy;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public ArrayList<Long> getEntityId() {
		return EntityId;
	}

	public void setEntityId(ArrayList<Long> entityId) {
		EntityId = entityId;
	}

	public ArrayList<Long> getPermissionId() {
		return PermissionId;
	}

	public void setPermissionId(ArrayList<Long> permissionId) {
		PermissionId = permissionId;
	}

	public ArrayList<Long> getRoleId() {
		return RoleId;
	}

	public void setRoleId(ArrayList<Long> roleId) {
		RoleId = roleId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public UserEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserEntity createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SPRequestEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SPRequestEntity(Long id, com.example.entities.UserEntity userId, ArrayList<Long> entityId,
			ArrayList<Long> permissionId, ArrayList<Long> roleId, String path,
			com.example.entities.UserEntity createdBy, Boolean isActive, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		EntityId = entityId;
		PermissionId = permissionId;
		RoleId = roleId;
		this.path = path;
		this.createdBy = createdBy;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
}
