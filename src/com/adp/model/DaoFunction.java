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
@Table(name = "DaoFunction")//声明在数据库中自动生成的表名
public class DaoFunction {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
    private Integer daoFunctionID;
	private String daoFunctionUrl;//方法名
	private String daoFunctionName;//业务名称
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tableFieldID")
	private TableField tableField;//操作的数据库表格以及字段信息
	
	private String daoFunctionType;//数据库操作类型
	
	@OneToMany(mappedBy = "daoFunction", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<DaoFunctionLog> daoFunctionLogList = new ArrayList<DaoFunctionLog>();
	
	@OneToMany(mappedBy = "daoFunction", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<DaoFunctionTableFieldRelation> daoFunctionTableFieldRelationList = new ArrayList<DaoFunctionTableFieldRelation>();

	public Integer getDaoFunctionID() {
		return daoFunctionID;
	}

	public void setDaoFunctionID(Integer daoFunctionID) {
		this.daoFunctionID = daoFunctionID;
	}

	public String getDaoFunctionUrl() {
		return daoFunctionUrl;
	}

	public void setDaoFunctionUrl(String daoFunctionUrl) {
		this.daoFunctionUrl = daoFunctionUrl;
	}

	public String getDaoFunctionName() {
		return daoFunctionName;
	}

	public void setDaoFunctionName(String daoFunctionName) {
		this.daoFunctionName = daoFunctionName;
	}

	public TableField getTableField() {
		return tableField;
	}

	public void setTableField(TableField tableField) {
		this.tableField = tableField;
	}

	public String getDaoFunctionType() {
		return daoFunctionType;
	}

	public void setDaoFunctionType(String daoFunctionType) {
		this.daoFunctionType = daoFunctionType;
	}

	public List<DaoFunctionLog> getDaoFunctionLogList() {
		return daoFunctionLogList;
	}

	public void setDaoFunctionLogList(List<DaoFunctionLog> daoFunctionLogList) {
		this.daoFunctionLogList = daoFunctionLogList;
	}

	public List<DaoFunctionTableFieldRelation> getDaoFunctionTableFieldRelationList() {
		return daoFunctionTableFieldRelationList;
	}

	public void setDaoFunctionTableFieldRelationList(
			List<DaoFunctionTableFieldRelation> daoFunctionTableFieldRelationList) {
		this.daoFunctionTableFieldRelationList = daoFunctionTableFieldRelationList;
	}

	
	
}
