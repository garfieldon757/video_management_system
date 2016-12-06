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
@Table(name = "DaoFunction")//声明在数据库中自动生成的表名
public class DaoFunction {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
    private Integer daoFunctionID;
	private String daoFunctionName;
	private String daoFunctionDescription;
	
	@OneToMany(mappedBy = "daoFunction", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<DaoFunctionLog> daoFunctionLog = new ArrayList<DaoFunctionLog>();
	
	
	public Integer getDaoFunctionID() {
		return daoFunctionID;
	}
	public void setDaoFunctionID(Integer daoFunctionID) {
		this.daoFunctionID = daoFunctionID;
	}
	public String getDaoFunctionName() {
		return daoFunctionName;
	}
	public void setDaoFunctionName(String daoFunctionName) {
		this.daoFunctionName = daoFunctionName;
	}
	public String getDaoFunctionDescription() {
		return daoFunctionDescription;
	}
	public void setDaoFunctionDescription(String daoFunctionDescription) {
		this.daoFunctionDescription = daoFunctionDescription;
	}

	public List<DaoFunctionLog> getDaoFunctionLog() {
		return daoFunctionLog;
	}
	public void setDaoFunctionLog(List<DaoFunctionLog> daoFunctionLog) {
		this.daoFunctionLog = daoFunctionLog;
	}
	
}
