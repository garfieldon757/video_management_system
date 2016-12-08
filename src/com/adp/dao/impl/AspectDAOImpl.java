package com.adp.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.AspectDAO;
import com.adp.model.ControllerFunction;
import com.adp.model.ControllerFunctionLog;
import com.adp.model.DaoFunction;
import com.adp.model.DaoFunctionLog;
import com.adp.model.DaoFunctionTableFieldRelation;
import com.adp.model.ServiceFunction;
import com.adp.model.ServiceFunctionLog;
import com.adp.model.User;

@Repository("asd")
@Transactional
public class AspectDAOImpl implements AspectDAO {

	@PersistenceContext(name="un")
	private EntityManager em ;

	@Override
	public void addDaoFunctionLog(User user, DaoFunction daoFunction) {
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String dateTime = df.format(now);
		
		DaoFunctionLog dfl = new DaoFunctionLog();
		dfl.setUser(user);
		dfl.setDaoFunction(daoFunction);
		dfl.setDateTime(dateTime);
		
//		String daoFunctionType = daoFunction.getDaoFunctionType();
//		switch(daoFunctionType)
//		{
//			case "insert":
//				break;
//			case "delete":
//				break;
//			case "update":
////				int daoFunctionID = daoFunction.getDaoFunctionID();
////				DaoFunctionTableFieldRelation dftfr = getDaoFunctionTableFieldRelationByDaoFunctionID(daoFunctionID);
//				//??
//				break;
//			case "select":
//				break;
//			default:
//				break;
//		}
		
		
		em.persist(dfl);
		return ;
	}

	@Override
	public DaoFunction getDaoFunction(String daoFunctionUrl) {
		String jpql = "select df from DaoFunction df where df.daoFunctionUrl =:daoFunctionUrl";
		DaoFunction df = (DaoFunction) em.createQuery(jpql).setParameter("daoFunctionUrl", daoFunctionUrl)
																							.getResultList().get(0);
		return df;
	}

	@Override
	public DaoFunctionTableFieldRelation getDaoFunctionTableFieldRelationByDaoFunctionID(int daoFunctionID) {
		String jpql = "select dftfr from DaoFunctionTableFieldRelation dftfr where dftfr.daoFunctionID =:daoFunctionID";
		DaoFunctionTableFieldRelation dftfr = (DaoFunctionTableFieldRelation) em.createQuery(jpql)
																															.setParameter( "daoFunctionID" , daoFunctionID)
																															.getResultList();
		return dftfr;
	}

	@Override
	public ControllerFunction getControllerFunction(String controllerFunctionUrl) {
		String jpql = "select cf from ControllerFunction cf where cf.controllerFunctionUrl =:controllerFunctionUrl";
		ControllerFunction cf =  (ControllerFunction) em.createQuery(jpql).setParameter("controllerFunctionUrl", controllerFunctionUrl)
																							.getResultList().get(0);
		return cf;
	}

	@Override
	public void addControllerFunctionLog(String dateTimeStart, String dateTimeEnd, User user,
																	ControllerFunction controllerFunction) {

		ControllerFunctionLog controllerFunctionLog = new ControllerFunctionLog();
		controllerFunctionLog.setDateTimeStart(dateTimeStart);
		controllerFunctionLog.setDateTimeEnd(dateTimeEnd);
		controllerFunctionLog.setControllerFunction(controllerFunction);
		controllerFunctionLog.setUser(user);
		
		em.persist(controllerFunctionLog);
		return ;
	}

	@Override
	public ServiceFunction getServiceFunction(String serviceFunctionUrl) {
		String jpql = "select sf from ServiceFunction sf where sf.serviceFunctionUrl =:serviceFunctionUrl";
		ServiceFunction sf =  (ServiceFunction) em.createQuery(jpql).setParameter("serviceFunctionUrl", serviceFunctionUrl)
																							.getResultList().get(0);
		return sf;
	}

	@Override
	public void addServiceFunctionLog(String dateTimeStart, String dateTimeEnd, User user,
																ServiceFunction serviceFunction) {
			
			ServiceFunctionLog serviceFunctionLog = new ServiceFunctionLog();
			serviceFunctionLog.setDateTimeStart(dateTimeStart);
			serviceFunctionLog.setDateTimeEnd(dateTimeEnd);
			serviceFunctionLog.setServiceFunction(serviceFunction);
			serviceFunctionLog.setUser(user);
			em.persist(serviceFunctionLog);
			return ;
	}
	
	
	
}
