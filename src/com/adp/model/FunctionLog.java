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
@Table(name = "FunctionLog")
public class FunctionLog {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer functionLogID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="functionID")
	private Function function;
	
	private String dateTimeStart;
	private String dateTimeEnd;
	
	//建立daoFunctionIpdateDetail
	@OneToMany(mappedBy = "functionLog", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<DaoFunctionUpdateDetail> daoFunctionUpdateDetailList = new ArrayList<DaoFunctionUpdateDetail>();

	
	
	public Integer getFunctionLogID() {
		return functionLogID;
	}

	public void setFunctionLogID(Integer functionLogID) {
		this.functionLogID = functionLogID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public String getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(String dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public List<DaoFunctionUpdateDetail> getDaoFunctionUpdateDetailList() {
		return daoFunctionUpdateDetailList;
	}

	public void setDaoFunctionUpdateDetailList(List<DaoFunctionUpdateDetail> daoFunctionUpdateDetailList) {
		this.daoFunctionUpdateDetailList = daoFunctionUpdateDetailList;
	}

	
}
