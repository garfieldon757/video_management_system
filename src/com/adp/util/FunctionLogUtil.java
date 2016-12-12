package com.adp.util;


import java.util.List;
import com.adp.model.FunctionLog;

public class FunctionLogUtil {

	private FunctionLog functionLog;
	private List<FunctionLogUtil> subFunctionLogUtilList;
	
	
	public FunctionLog getFunctionLog() {
		return functionLog;
	}
	public void setFunctionLog(FunctionLog functionLog) {
		this.functionLog = functionLog;
	}
	public List<FunctionLogUtil> getSubFunctionLogUtilList() {
		return subFunctionLogUtilList;
	}
	public void setSubFunctionLogUtilList(List<FunctionLogUtil> subFunctionLogUtilList) {
		this.subFunctionLogUtilList = subFunctionLogUtilList;
	}
	
}
