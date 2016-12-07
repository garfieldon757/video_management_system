package com.adp.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.AspectDAO;
import com.adp.model.DaoFunction;
import com.adp.model.DaoFunctionLog;
import com.adp.model.DaoFunctionTableFieldRelation;
import com.adp.model.User;

@Repository("asd")
@Transactional
public class AspectDAOImpl implements AspectDAO {

	@PersistenceContext(name="un")
	private EntityManager em ;

	@Override
	public void addDaoFunctionLog(User user, DaoFunction daoFunction) {
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		
	}

	@Override
	public DaoFunction getDaoFunction(String daoFunctionName) {
		String jpql = "select df from DaoFunction df where df.daoFunctionName =:daoFunctionName";
		DaoFunction df = (DaoFunction) em.createQuery(jpql).setParameter("daoFunctionName", daoFunctionName)
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
	
	
	
}
