package com.adp.util;


import java.util.List;
import com.adp.model.FunctionLog;

public class FunctionLogUtil {

	private FunctionLog functionLog;
	private List<FunctionLogUtil> subFunctionLogUtilList;
	private List<FunctionLog> subFunctionLogList;//dao层使用
	

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
	
	public List<FunctionLog> getSubFunctionLogList() {
		return subFunctionLogList;
	}
	public void setSubFunctionLogList(List<FunctionLog> subFunctionLogList) {
		this.subFunctionLogList = subFunctionLogList;
	}
}
