package com.adp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adp.dao.AspectDAO;
import com.adp.dao.UserDAO;
import com.adp.model.DaoFunction;
import com.adp.model.User;

@Component
@Aspect
public class DaoAspect {

	@Autowired(required=true)
	private AspectDAO aspectDAO;
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Pointcut("execution( public void com.adp.dao.impl.UserDAOImpl.testDaoAspect(..) )") 
	public void testDaoAspect()
	{

	}

@Before(  "testDaoAspect()" )
	public void enableFilters(JoinPoint joinPoint)
	{
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");//获取user对象
		User user = userDAO.getUserByID(1);
		DaoFunction daoFunction = aspectDAO.getDaoFunction("testDaoAspect");//获取DaoFunction对象
		
		aspectDAO.addDaoFunctionLog(user, daoFunction);
		System.out.println("This is aspect ,which runs before testDaoAspect() !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}	
	
}
