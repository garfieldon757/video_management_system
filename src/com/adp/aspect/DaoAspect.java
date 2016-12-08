package com.adp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.adp.dao.AspectDAO;
import com.adp.model.DaoFunction;
import com.adp.model.User;

@Component
@Aspect
public class DaoAspect {

	@Autowired(required=true)
	private AspectDAO aspectDAO;
	
	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.updateUser(..) )") 
	public void Dao_Aspect()
	{	}
	
	@Around(  value="Dao_Aspect()" )
	public Object Dao_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
	{
		
		Object result = joinPoint.proceed();
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session=attr.getRequest().getSession(true);
		User user = (User) session.getAttribute("user");

		String daoFunctionUrl = joinPoint.getSignature().getName();
		DaoFunction daoFunction = aspectDAO.getDaoFunction( daoFunctionUrl );//获取DaoFunction对象
		aspectDAO.addDaoFunctionLog(user, daoFunction);
		
		return result;
	}	

//	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.*(..) )") 
//	public void UserDAOImpl_Aspect()
//	{	}
//	
//
//	
//	@AfterReturning(  value="UserDAOImpl_Aspect()" , returning="returnValue" )
//	public void UserDAOImpl_Aspect(JoinPoint joinPoint , Object returnValue)
//	{
//		
//		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session=attr.getRequest().getSession(true);
//		User user = (User) session.getAttribute("user");
//
//		String daoFunctionName = joinPoint.getSignature().getName();
//		DaoFunction daoFunction = aspectDAO.getDaoFunction( daoFunctionName );//获取DaoFunction对象
//		System.out.println(returnValue);
//		aspectDAO.addDaoFunctionLog(user, daoFunction);
//		
//	}	
	
	
	
}
