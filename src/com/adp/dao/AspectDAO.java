package com.adp.dao;

import com.adp.model.DaoFunctionUpdateDetail;
import com.adp.model.Function;
import com.adp.model.TableField;
import com.adp.model.User;

public interface AspectDAO {

	//public void addDaoFunctionLog(User user , DaoFunction daoFunction);
	public DaoFunctionLog getDaoFunctionLogByDaoFunctionLogID(int daoFunctionLogID);
	public TableField getTableFieldByField(String fieldName);
	//public DaoFunction getDaoFunction(String daoFunctionUrl);
	
	public DaoFunctionUpdateDetail getDaoFunctionUpdateDetailByDaoFunctionID( int daoFunctionID );
	public void addDaoFunctionUpdateDetail( int daoFunctionLogID , String fieldName , String oldValue , String newValue );
	
	//public ControllerFunction getControllerFunction(String controllerFunctionUrl);
	
	
	//public ServiceFunction getServiceFunction(String serviceFunctionUrl);
	//public void addServiceFunctionLog(String dateTimeStart, String dateTimeEnd, User user, ServiceFunction serviceFunction);
	
	//整理是重构之后的新方法
	public Function getFunction(String functionUrl);
	public void addFunctionLog(String dateTimeStart, String dateTimeEnd, User user, Function function );
}
