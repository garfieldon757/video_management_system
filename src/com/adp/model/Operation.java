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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "Operation")
public class Operation {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer operationID;
	
	private String operationName;
	private String operationValue;
	
	@OneToMany(mappedBy = "operation", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Authorization> authList= new ArrayList<Authorization>();
	
	public Integer getOperationID() {
		return operationID;
	}
	public void setOperationID(Integer operationID) {
		this.operationID = operationID;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationValue() {
		return operationValue;
	}
	public void setOperationValue(String operationValue) {
		this.operationValue = operationValue;
	}
	
	
	
}
