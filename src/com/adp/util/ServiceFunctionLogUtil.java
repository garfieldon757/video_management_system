package com.adp.util;

import java.util.List;

import com.adp.model.DaoFunctionLog;
import com.adp.model.ServiceFunctionLog;

public class ServiceFunctionLogUtil{
	
	private ServiceFunctionLog serviceFunctionLog;
	private List<DaoFunctionLog> daoFunctionLogList;
	
	public ServiceFunctionLog getServiceFunctionLog() {
		return serviceFunctionLog;
	}
	public void setServiceFunctionLog(ServiceFunctionLog serviceFunctionLog) {
		this.serviceFunctionLog = serviceFunctionLog;
	}
	public List<DaoFunctionLog> getDaoFunctionLogList() {
		return daoFunctionLogList;
	}
	public void setDaoFunctionLogList(List<DaoFunctionLog> daoFunctionLogList) {
		this.daoFunctionLogList = daoFunctionLogList;
	}
	
	
	
}
