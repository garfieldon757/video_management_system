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
@Table(name = "ControllerFunction")//声明在数据库中自动生成的表名
public class ControllerFunction {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer controllerFunctionID;
	private String controllerFunctionUrl;
	private String controllerFunctionName;
	
	@OneToMany(mappedBy = "controllerFunction", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<ControllerFunctionLog> controllerFunctionLogList = new ArrayList<ControllerFunctionLog>();

	public Integer getControllerFunctionID() {
		return controllerFunctionID;
	}

	public void setControllerFunctionID(Integer controllerFunctionID) {
		this.controllerFunctionID = controllerFunctionID;
	}

	public String getControllerFunctionUrl() {
		return controllerFunctionUrl;
	}

	public void setControllerFunctionUrl(String controllerFunctionUrl) {
		this.controllerFunctionUrl = controllerFunctionUrl;
	}

	public String getControllerFunctionName() {
		return controllerFunctionName;
	}

	public void setControllerFunctionName(String controllerFunctionName) {
		this.controllerFunctionName = controllerFunctionName;
	}

	public List<ControllerFunctionLog> getControllerFunctionLogList() {
		return controllerFunctionLogList;
	}

	public void setControllerFunctionLogList(List<ControllerFunctionLog> controllerFunctionLogList) {
		this.controllerFunctionLogList = controllerFunctionLogList;
	}
	
}
