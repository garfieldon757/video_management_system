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
@Table(name = "DaoFunctionUpdateDetail")//声明在数据库中自动生成的表名
public class DaoFunctionUpdateDetail {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer daoFunctionUpdateDetailID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="functionLogID")
	private FunctionLog functionLog;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tableFieldID")
	private TableField tableField;
	
	private String oldValue;
	private String newValue;
	
	

	public Integer getDaoFunctionUpdateDetailID() {
		return daoFunctionUpdateDetailID;
	}
	public void setDaoFunctionUpdateDetailID(Integer daoFunctionUpdateDetailID) {
		this.daoFunctionUpdateDetailID = daoFunctionUpdateDetailID;
	}
	public FunctionLog getFunctionLog() {
		return functionLog;
	}
	public void setFunctionLog(FunctionLog functionLog) {
		this.functionLog = functionLog;
	}
	public TableField getTableField() {
		return tableField;
	}
	public void setTableField(TableField tableField) {
		this.tableField = tableField;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
}
