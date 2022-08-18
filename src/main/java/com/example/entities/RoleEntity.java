package com.example.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name="roles")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE roles SET is_active=false WHERE id=?")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="isActive")
	private Boolean isActive=true;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private Date updatedAt;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.role", cascade = CascadeType.ALL)
	private List<UserRoleEntity> userRole;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleEntity(Long id, String roleName, String description, Boolean isActive, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
}
