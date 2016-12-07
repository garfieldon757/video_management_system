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
@Table(name = "DaoFunctionTableFieldRelation")//声明在数据库中自动生成的表名
public class DaoFunctionTableFieldRelation {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer daoFunctionTableFieldRelationID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="daoFunctionID")
	private DaoFunction daoFunction;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tableFieldID")
	private TableField tableField;

	public Integer getDaoFunctionTableFieldRelationID() {
		return daoFunctionTableFieldRelationID;
	}

	public void setDaoFunctionTableFieldRelationID(Integer daoFunctionTableFieldRelationID) {
		this.daoFunctionTableFieldRelationID = daoFunctionTableFieldRelationID;
	}

	public DaoFunction getDaoFunction() {
		return daoFunction;
	}

	public void setDaoFunction(DaoFunction daoFunction) {
		this.daoFunction = daoFunction;
	}

	public TableField getTableField() {
		return tableField;
	}

	public void setTableField(TableField tableField) {
		this.tableField = tableField;
	}
	
	
	
}
