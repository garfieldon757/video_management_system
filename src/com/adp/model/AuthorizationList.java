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
@Table(name = "AuthorizationList")
public class AuthorizationList {
	
	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer authListID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="giveAuthUserID")
	private User giveAuthUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="applyAuthUserID")
	private User applyAuthUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="roleID")
    private Role role;
	
	private int authStatus;
	
	public AuthorizationList() {
		super();
	}

	public AuthorizationList(User giveAuthUser, User applyAuthUser, Role role, int authStatus) {
		super();
		this.giveAuthUser = giveAuthUser;
		this.applyAuthUser = applyAuthUser;
		this.role = role;
		this.authStatus = authStatus;
	}
	
	public Integer getAuthListID() {
		return authListID;
	}
	public void setAuthListID(Integer authListID) {
		this.authListID = authListID;
	}
	public User getGiveAuthUser() {
		return giveAuthUser;
	}
	public void setGiveAuthUser(User giveAuthUser) {
		this.giveAuthUser = giveAuthUser;
	}
	public User getApplyAuthUser() {
		return applyAuthUser;
	}
	public void setApplyAuthUser(User applyAuthUser) {
		this.applyAuthUser = applyAuthUser;
	}
	public int getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
