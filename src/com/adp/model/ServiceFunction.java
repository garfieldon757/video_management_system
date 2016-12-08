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
@Table(name = "ServiceFunction")//声明在数据库中自动生成的表名
public class ServiceFunction {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer serviceFunctionID;
	private String serviceFunctionUrl;
	private String serviceFunctionName;
	
	@OneToMany(mappedBy = "serviceFunction", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<ServiceFunctionLog> serviceFunctionLogList = new ArrayList<ServiceFunctionLog>();

	public Integer getServiceFunctionID() {
		return serviceFunctionID;
	}

	public void setServiceFunctionID(Integer serviceFunctionID) {
		this.serviceFunctionID = serviceFunctionID;
	}

	public String getServiceFunctionUrl() {
		return serviceFunctionUrl;
	}

	public void setServiceFunctionUrl(String serviceFunctionUrl) {
		this.serviceFunctionUrl = serviceFunctionUrl;
	}

	public String getServiceFunctionName() {
		return serviceFunctionName;
	}

	public void setServiceFunctionName(String serviceFunctionName) {
		this.serviceFunctionName = serviceFunctionName;
	}

	public List<ServiceFunctionLog> getServiceFunctionLogList() {
		return serviceFunctionLogList;
	}

	public void setServiceFunctionLogList(List<ServiceFunctionLog> serviceFunctionLogList) {
		this.serviceFunctionLogList = serviceFunctionLogList;
	}
	
	
	
}
