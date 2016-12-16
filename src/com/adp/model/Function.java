package com.adp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "Function",
indexes = {
		@Index( name="functionIndex",
					  columnList=  "functionType , functionUrl, fatherFunctionID,resourceID"
					)
				}
		)
public class Function {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer functionID;
	private String functionUrl;
	private String functionName;
	private String functionType;
	private String daoFunctionOpetationType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resourceID")
	private Resource resource;
	
	@OneToMany(mappedBy = "function", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<FunctionLog> functionLogList = new ArrayList<FunctionLog>();
	
	//建立自己对自己的外键引用
	@OneToMany(mappedBy = "function", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Function> functionList = new ArrayList<Function>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fatherFunctionID")
	private Function function;

	public Integer getFunctionID() {
		return functionID;
	}

	public void setFunctionID(Integer functionID) {
		this.functionID = functionID;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getDaoFunctionOpetationType() {
		return daoFunctionOpetationType;
	}

	public void setDaoFunctionOpetationType(String daoFunctionOpetationType) {
		this.daoFunctionOpetationType = daoFunctionOpetationType;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<FunctionLog> getFunctionLogList() {
		return functionLogList;
	}

	public void setFunctionLogList(List<FunctionLog> functionLogList) {
		this.functionLogList = functionLogList;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	
}
