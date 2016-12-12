package com.adp.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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

//	@Override
//	public void addDaoFunctionLog(User user, DaoFunction daoFunction) {
//		
//		Timestamp now = new Timestamp(System.currentTimeMillis()); 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		String dateTime = df.format(now);
//		
//		DaoFunctionLog dfl = new DaoFunctionLog();
//		dfl.setUser(user);
//		dfl.setDaoFunction(daoFunction);
//		dfl.setDateTime(dateTime);
//		
////		String daoFunctionType = daoFunction.getDaoFunctionType();
////		switch(daoFunctionType)
////		{
////			case "insert":
////				break;
////			case "delete":
////				break;
////			case "update":
//////				int daoFunctionID = daoFunction.getDaoFunctionID();
//////				DaoFunctionTableFieldRelation dftfr = getDaoFunctionTableFieldRelationByDaoFunctionID(daoFunctionID);
////				//??
////				break;
////			case "select":
////				break;
////			default:
////				break;
////		}
//		
//		
//		em.persist(dfl);
//		return ;
//	}

//	@Override
//	public DaoFunction getDaoFunction(String daoFunctionUrl) {
//		String jpql = "select df from DaoFunction df where df.daoFunctionUrl =:daoFunctionUrl";
//		DaoFunction df = (DaoFunction) em.createQuery(jpql).setParameter("daoFunctionUrl", daoFunctionUrl)
//																							.getResultList().get(0);
//		return df;
//	}

	@Override
	public DaoFunctionUpdateDetail getDaoFunctionUpdateDetailByDaoFunctionID(int daoFunctionID) {
		String jpql = "select dfud from DaoFunctionUpdateDetail dfud where dfud.daoFunctionID =:daoFunctionID";
		DaoFunctionUpdateDetail dftfr = (DaoFunctionUpdateDetail) em.createQuery(jpql)
																															.setParameter( "daoFunctionID" , daoFunctionID)
																															.getResultList();
		return dftfr;
	}

//	@Override
//	public ControllerFunction getControllerFunction(String controllerFunctionUrl) {
//		String jpql = "select cf from ControllerFunction cf where cf.controllerFunctionUrl =:controllerFunctionUrl";
//		ControllerFunction cf =  (ControllerFunction) em.createQuery(jpql).setParameter("controllerFunctionUrl", controllerFunctionUrl)
//																							.getResultList().get(0);
//		return cf;
//	}



//	@Override
//	public ServiceFunction getServiceFunction(String serviceFunctionUrl) {
//		String jpql = "select sf from ServiceFunction sf where sf.serviceFunctionUrl =:serviceFunctionUrl";
//		ServiceFunction sf =  (ServiceFunction) em.createQuery(jpql).setParameter("serviceFunctionUrl", serviceFunctionUrl)
//																							.getResultList().get(0);
//		return sf;
//	}

//	@Override
//	public void addServiceFunctionLog(String dateTimeStart, String dateTimeEnd, User user,
//																ServiceFunction serviceFunction) {
//			
//			ServiceFunctionLog serviceFunctionLog = new ServiceFunctionLog();
//			serviceFunctionLog.setDateTimeStart(dateTimeStart);
//			serviceFunctionLog.setDateTimeEnd(dateTimeEnd);
//			serviceFunctionLog.setServiceFunction(serviceFunction);
//			serviceFunctionLog.setUser(user);
//			em.persist(serviceFunctionLog);
//			return ;
//	}

	@Override
	public void addDaoFunctionUpdateDetail(int daoFunctionLogID, String fieldName, String oldValue, String newValue) {
		
		DaoFunctionUpdateDetail daoFunctionUpdateDetail  = new DaoFunctionUpdateDetail();
		DaoFunctionLog daoFunctionLog = getDaoFunctionLogByDaoFunctionLogID(daoFunctionLogID);
		daoFunctionUpdateDetail.setDaoFunctionLog(daoFunctionLog);
		TableField tableField = getTableFieldByField(fieldName);
		daoFunctionUpdateDetail.setTableField(tableField);
		daoFunctionUpdateDetail.setOldValue(oldValue);
		daoFunctionUpdateDetail.setNewValue(newValue);
		em.persist(daoFunctionUpdateDetail);
		return ;
	}

	@Override
	public DaoFunctionLog getDaoFunctionLogByDaoFunctionLogID(int daoFunctionLogID) {
		String jpql = "select dfl from DaoFunctionLog dfl where dfl.daoFunctionLogID =:daoFunctionLogID";
		DaoFunctionLog daoFunctionLog = (DaoFunctionLog) em.createQuery(jpql).setParameter("daoFunctionLogID", daoFunctionLogID)
																								.getResultList().get(0);
		return daoFunctionLog;
	}

	@Override
	public TableField getTableFieldByField(String fieldName) {
		String jpql = "select tf from TableField tf where tf.fieldName =:fieldName";
		TableField tableField = (TableField) em.createQuery(jpql).setParameter("fieldName", fieldName)
																				.getResultList().get(0);
		return tableField;
	}
	
	//从这里开始，是重构之后开始写的方法
	
	@Override
	public Function getFunction(String functionUrl) {
		String jpql = "select f from Function f where f.functionUrl =:functionUrl";
		Function f =  (Function) em.createQuery(jpql).setParameter("functionUrl", functionUrl)
																							.getResultList().get(0);
		return f;
	}
	
	@Override
	public void addFunctionLog(String dateTimeStart, String dateTimeEnd, User user,Function function) {

		FunctionLog functionLog = new FunctionLog();
		functionLog.setDateTimeStart(dateTimeStart);
		functionLog.setDateTimeEnd(dateTimeEnd);
		functionLog.setFunction(function);
		functionLog.setUser(user);
		
		em.persist(functionLog);
		return ;
	}
	
}
