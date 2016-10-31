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
@Table(name = "ProcessLog")//声明在数据库中自动生成的表名为User
public class ProcessLog {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer processID;
	
	private String processType;
	private String processResultUrl;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="videoID")
	private Video video;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="algorithmID")
	private Algorithm algorithm;
	
	
	public Integer getProcessID() {
		return processID;
	}
	public void setProcessID(Integer processID) {
		this.processID = processID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Algorithm getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getProcessResultUrl() {
		return processResultUrl;
	}
	public void setProcessResultUrl(String processResultUrl) {
		this.processResultUrl = processResultUrl;
	}
	
	
	
}
