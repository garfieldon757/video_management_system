package com.adp.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "Role")//声明在数据库中自动生成的表名为User
public class Role {
	
	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer roleID;
	private String roleName;
	
	@OneToMany(mappedBy = "role", cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	private List<User> userList = new ArrayList<User>();
	
	
	@OneToMany(mappedBy = "role", cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	private List<AuthorizationList> authorizatioinList = new ArrayList<AuthorizationList>();
	
	
	@OneToMany(mappedBy = "role", cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	private List<AuthorizationRoleRelation> authRoleRelationList = new ArrayList<AuthorizationRoleRelation>();
	
	public Role() {
		super();
	}
	
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public List<AuthorizationList> getAuthorizatioinList() {
		return authorizatioinList;
	}
	public void setAuthorizatioinList(List<AuthorizationList> authorizatioinList) {
		this.authorizatioinList = authorizatioinList;
	}
	
	public List<AuthorizationRoleRelation> getAuthRoleRelationList() {
		return authRoleRelationList;
	}
	public void setAuthRoleRelationList(List<AuthorizationRoleRelation> authRoleRelationList) {
		this.authRoleRelationList = authRoleRelationList;
	}
}
