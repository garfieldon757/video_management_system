package com.adp.dao;

import com.adp.model.DaoFunction;
import com.adp.model.DaoFunctionTableFieldRelation;
import com.adp.model.User;

public interface AspectDAO {

	public void addDaoFunctionLog(User user , DaoFunction daoFunction);
	public DaoFunction getDaoFunction(String daoFunctionName);
	public DaoFunctionTableFieldRelation getDaoFunctionTableFieldRelationByDaoFunctionID( int daoFunctionID );
}
