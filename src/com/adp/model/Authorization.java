package com.adp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "Authorization")
public class Authorization {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer authID;
	private String authName;
	
	@OneToMany(mappedBy = "authorization", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	private List<AuthorizationRoleRelation> authRoleRelationList= new ArrayList<AuthorizationRoleRelation>();
	
	public List<AuthorizationRoleRelation> getAuthRoleRelationList() {
		return authRoleRelationList;
	}
	public void setAuthRoleRelationList(List<AuthorizationRoleRelation> authRoleRelationList) {
		this.authRoleRelationList = authRoleRelationList;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resourceID")
	private Resource resource;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="operationID")
	private Operation operation;
	
	public Integer getAuthID() {
		return authID;
	}
	public void setAuthID(Integer authID) {
		this.authID = authID;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
}
