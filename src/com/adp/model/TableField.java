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
@Table(name = "TableField")//声明在数据库中自动生成的表名
public class TableField {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer tableFieldID;
	private String tableName;
	private String fieldName;
	
	@OneToMany(mappedBy = "daoFunction", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<DaoFunctionTableFieldRelation> daoFunctionTableFieldRelationList = new ArrayList<DaoFunctionTableFieldRelation>();

	public Integer getTableFieldID() {
		return tableFieldID;
	}

	public void setTableFieldID(Integer tableFieldID) {
		this.tableFieldID = tableFieldID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<DaoFunctionTableFieldRelation> getDaoFunctionTableFieldRelationList() {
		return daoFunctionTableFieldRelationList;
	}

	public void setDaoFunctionTableFieldRelationList(
			List<DaoFunctionTableFieldRelation> daoFunctionTableFieldRelationList) {
		this.daoFunctionTableFieldRelationList = daoFunctionTableFieldRelationList;
	}

	
	
	
}
