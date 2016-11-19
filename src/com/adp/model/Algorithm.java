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
@Table(name = "Algorithm")//声明在数据库中自动生成的表名为User
public class Algorithm {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer algorithmID;
	
	private String algorithmName;
	private String executeInstraction;
	private String fileUrl;
	private String sampleExecInstraction;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="algorithmCategoryID")
	private AlgorithmCategory algorithmCategory;
	
	@OneToMany(mappedBy = "algorithm", cascade=CascadeType.MERGE , fetch=FetchType.EAGER)
	private List<ProcessLog> processLogList = new ArrayList<ProcessLog>();

	
	public Integer getAlgorithmID() {
		return algorithmID;
	}

	public void setAlgorithmID(Integer algorithmID) {
		this.algorithmID = algorithmID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AlgorithmCategory getAlgorithmCategory() {
		return algorithmCategory;
	}

	public void setAlgorithmCategory(AlgorithmCategory algorithmCategory) {
		this.algorithmCategory = algorithmCategory;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getExecuteInstraction() {
		return executeInstraction;
	}

	public void setExecuteInstraction(String executeInstraction) {
		this.executeInstraction = executeInstraction;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getSampleExecInstraction() {
		return sampleExecInstraction;
	}

	public void setSampleExecInstraction(String sampleExecInstraction) {
		this.sampleExecInstraction = sampleExecInstraction;
	}

	public List<ProcessLog> getProcessLogList() {
		return processLogList;
	}

	public void setProcessLogList(List<ProcessLog> processLogList) {
		this.processLogList = processLogList;
	}
	

	
	
	
	
	
}
