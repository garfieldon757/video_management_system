package com.adp.util;

import java.util.List;

import com.adp.model.ControllerFunctionLog;

public class ControllerFunctionLogUtil {

	private ControllerFunctionLog controllerFunctionLog;
	private List<ServiceFunctionLogUtil> serviceFunctionLogUtilList;
	
	public ControllerFunctionLog getControllerFunctionLog() {
		return controllerFunctionLog;
	}
	public void setControllerFunctionLog(ControllerFunctionLog controllerFunctionLog) {
		this.controllerFunctionLog = controllerFunctionLog;
	}
	public List<ServiceFunctionLogUtil> getServiceFunctionLogUtilList() {
		return serviceFunctionLogUtilList;
	}
	public void setServiceFunctionLogUtilList(List<ServiceFunctionLogUtil> serviceFunctionLogUtilList) {
		this.serviceFunctionLogUtilList = serviceFunctionLogUtilList;
	} 
	
	
}


