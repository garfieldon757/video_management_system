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
@Table(name = "ServiceFunctionLog")//声明在数据库中自动生成的表名
public class ServiceFunctionLog {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)//根据不同数据库自动�?�择合�?�的id生成方案，这里使用mysql,为�?�增�?
	private Integer serviceFunctionLogID;
	private String dateTimeStart;
	private String dateTimeEnd;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="serviceFunctionID")
	private ServiceFunction serviceFunction;

	public Integer getServiceFunctionLogID() {
		return serviceFunctionLogID;
	}

	public void setServiceFunctionLogID(Integer serviceFunctionLogID) {
		this.serviceFunctionLogID = serviceFunctionLogID;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServiceFunction getServiceFunction() {
		return serviceFunction;
	}

	public void setServiceFunction(ServiceFunction serviceFunction) {
		this.serviceFunction = serviceFunction;
	}
	
	
	
}
