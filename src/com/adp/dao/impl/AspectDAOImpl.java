package com.adp.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.AspectDAO;
import com.adp.model.DaoFunctionUpdateDetail;
import com.adp.model.Function;
import com.adp.model.FunctionLog;
import com.adp.model.TableField;
import com.adp.model.User;

@Repository("asd")
@Transactional
public class AspectDAOImpl implements AspectDAO {

	@PersistenceContext(name="un")
	private EntityManager em ;

	
	//从这里开始，是重构之后开始写的方法
	
	@Override
	public Function getFunction(String functionUrl , String functionType) {
		String jpql = "select f from Function f where f.functionUrl =:functionUrl and f.functionType =:functionType ";
		List<Function> fList = em.createQuery(jpql).setParameter("functionUrl", functionUrl)
																			.setParameter("functionType", functionType)
																			.getResultList();
		if(fList.size() != 0){
			return fList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public Integer addFunctionLog(String dateTimeStart, String dateTimeEnd, User user,Function function) {

		FunctionLog functionLog = new FunctionLog();
		functionLog.setDateTimeStart(dateTimeStart);
		functionLog.setDateTimeEnd(dateTimeEnd);
		functionLog.setFunction(function);
		functionLog.setUser(user);
		
		em.persist(functionLog);
		int test = functionLog.getFunctionLogID();
		return functionLog.getFunctionLogID();
	}
	
	@Override
	public FunctionLog getFunctionLogByFunctionLogID(int functionLogID) {
		String jpql = "select fl from FunctionLog fl where fl.functionLogID =:functionLogID";
		FunctionLog functionLog = (FunctionLog) em.createQuery(jpql).setParameter("functionLogID", functionLogID)
																								.getResultList().get(0);
		return functionLog;
	}
	
	@Override
	public TableField getTableFieldByFieldName(String fieldName) {
		String jpql = "select tf from TableField tf where tf.fieldName =:fieldName";
		TableField tableField = (TableField) em.createQuery(jpql).setParameter("fieldName", fieldName)
																				.getResultList().get(0);
		return tableField;
	}
	
	@Override
	public void addDaoFunctionUpdateDetail(int daoFunctionLogID, String fieldName, String oldValue, String newValue) {
		
		DaoFunctionUpdateDetail daoFunctionUpdateDetail  = new DaoFunctionUpdateDetail();
		FunctionLog daoFunctionLog = getFunctionLogByFunctionLogID(daoFunctionLogID);
		daoFunctionUpdateDetail.setFunctionLog(daoFunctionLog);
		TableField tableField = getTableFieldByFieldName(fieldName);
		daoFunctionUpdateDetail.setTableField(tableField);
		daoFunctionUpdateDetail.setOldValue(oldValue);
		daoFunctionUpdateDetail.setNewValue(newValue);
		em.persist(daoFunctionUpdateDetail);
		return ;
	}
	
	@Override
	public DaoFunctionUpdateDetail getDaoFunctionUpdateDetailByDaoFunctionID(int daoFunctionID) {
		String jpql = "select dfud from DaoFunctionUpdateDetail dfud where dfud.daoFunctionID =:daoFunctionID";
		DaoFunctionUpdateDetail dftfr = (DaoFunctionUpdateDetail) em.createQuery(jpql)
																															.setParameter( "daoFunctionID" , daoFunctionID)
																															.getResultList();
		return dftfr;
	}
	
}
