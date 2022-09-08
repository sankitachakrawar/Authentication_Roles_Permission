package com.example.entities;

import java.sql.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "user_role")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE user_role SET is_active=false WHERE pk=?")
@AssociationOverrides({ @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")), @AssociationOverride(name = "pk.role", joinColumns = @JoinColumn(name = "role_id")) })
public class UserRoleEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private UserRoleId pk = new UserRoleId();

	private Boolean isActive = true;

	private Date createdAt;

	private Date updatedAt;

	@EmbeddedId
	public UserRoleId getPk() {

		return pk;

	}

	public void setPk(UserRoleId pk) {

		this.pk = pk;

	}

	@Column(name = "is_active")
	public Boolean getIsActive() {

		return isActive;

	}

	public void setIsActive(Boolean isActive) {

		this.isActive = isActive;

	}

	@Column(name = "created_at")
	@CreationTimestamp
	public Date getCreatedAt() {

		return createdAt;

	}

	public void setCreatedAt(Date createdAt) {

		this.createdAt = createdAt;

	}

	@Column(name = "updated_at")
	@UpdateTimestamp
	public Date getUpdatedAt() {

		return updatedAt;

	}

	public void setUpdatedAt(Date updatedAt) {

		this.updatedAt = updatedAt;

	}

}
