package com.adp.dao;

import com.adp.model.DaoFunctionUpdateDetail;
import com.adp.model.Function;
import com.adp.model.FunctionLog;
import com.adp.model.TableField;
import com.adp.model.User;

public interface AspectDAO {

	
	//整理是重构之后的新方法
	public Function getFunction(String functionUrl);
	public Integer addFunctionLog(String dateTimeStart, String dateTimeEnd, User user, Function function );
	public FunctionLog getFunctionLogByFunctionLogID(int FunctionLogID);
	public TableField getTableFieldByFieldName(String fieldName);
	public void addDaoFunctionUpdateDetail( int daoFunctionLogID, String fieldName, String oldValue, String newValue );
	public DaoFunctionUpdateDetail getDaoFunctionUpdateDetailByDaoFunctionID( int daoFunctionID );
}
