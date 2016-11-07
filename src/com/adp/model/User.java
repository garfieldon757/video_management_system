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
@Table(name = "User")//声明在数据库中自动生成的表名为User
public class User {
    
	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
    private Integer  userID;
    private String sex;
    private String userName;
    private String userPassword;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="roleID")
    private Role role;
    
    @OneToMany(mappedBy = "giveAuthUser", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
    private List<AuthorizationList> giveAuthUserList = new ArrayList<AuthorizationList>();
    
    @OneToMany(mappedBy = "applyAuthUser", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
    private List<AuthorizationList> applyAuthUserList = new ArrayList<AuthorizationList>();
    
    @OneToMany(mappedBy = "user", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Video> videoList = new ArrayList<Video>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Algorithm> algorithmList = new ArrayList<Algorithm>();
    
    @OneToMany(mappedBy = "user", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<ProcessLog> processLogList = new ArrayList<ProcessLog>();

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<AuthorizationList> getGiveAuthUserList() {
		return giveAuthUserList;
	}

	public void setGiveAuthUserList(List<AuthorizationList> giveAuthUserList) {
		this.giveAuthUserList = giveAuthUserList;
	}

	public List<AuthorizationList> getApplyAuthUserList() {
		return applyAuthUserList;
	}

	public void setApplyAuthUserList(List<AuthorizationList> applyAuthUserList) {
		this.applyAuthUserList = applyAuthUserList;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public List<Algorithm> getAlgorithmList() {
		return algorithmList;
	}

	public void setAlgorithmList(List<Algorithm> algorithmList) {
		this.algorithmList = algorithmList;
	}

	public List<ProcessLog> getProcessLogList() {
		return processLogList;
	}

	public void setProcessLogList(List<ProcessLog> processLogList) {
		this.processLogList = processLogList;
	}
    
}
