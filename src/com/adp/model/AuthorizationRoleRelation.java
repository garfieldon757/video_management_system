package com.adp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "AuthorizationRoleRelation")
public class AuthorizationRoleRelation {
	
	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer authRoleRelationID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="roleID")
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="authID")
	private Authorization authorization;
	
	public Integer getAuthRoleRelationID() {
		return authRoleRelationID;
	}
	public void setAuthRoleRelationID(Integer authRoleRelationID) {
		this.authRoleRelationID = authRoleRelationID;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Authorization getAuthorization() {
		return authorization;
	}
	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}
}
