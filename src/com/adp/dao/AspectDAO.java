package com.adp.dao;

import com.adp.model.ControllerFunction;
import com.adp.model.DaoFunction;
import com.adp.model.DaoFunctionTableFieldRelation;
import com.adp.model.ServiceFunction;
import com.adp.model.User;

public interface AspectDAO {

	public void addDaoFunctionLog(User user , DaoFunction daoFunction);
	public DaoFunction getDaoFunction(String daoFunctionUrl);
	public DaoFunctionTableFieldRelation getDaoFunctionTableFieldRelationByDaoFunctionID( int daoFunctionID );
	public ControllerFunction getControllerFunction(String controllerFunctionUrl);
	public void addControllerFunctionLog(String dateTimeStart, String dateTimeEnd, User user, ControllerFunction controllerFunction );
	public ServiceFunction getServiceFunction(String serviceFunctionUrl);
	public void addServiceFunctionLog(String dateTimeStart, String dateTimeEnd, User user, ServiceFunction serviceFunction);
}
