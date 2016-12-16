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
@Table(name = "AlgorithmCategory")//声明在数据库中自动生成的表名为User
public class AlgorithmCategory {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer algorithmCategoryID;
	
	private String algorithmCategoryName;
	 
	@OneToMany(mappedBy = "algorithmCategory", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JsonIgnore
	 private List<Algorithm> algorithmList = new ArrayList<Algorithm>();
	 
	public Integer getAlgorithmCategoryID() {
		return algorithmCategoryID;
	}
	
	public void setAlgorithmCategoryID(Integer algorithmCategoryID) {
		this.algorithmCategoryID = algorithmCategoryID;
	}
	public String getAlgorithmCategoryName() {
		return algorithmCategoryName;
	}
	public void setAlgorithmCategoryName(String algorithmCategoryName) {
		this.algorithmCategoryName = algorithmCategoryName;
	}
	
}
