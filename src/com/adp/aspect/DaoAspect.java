package com.adp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.adp.dao.AspectDAO;
import com.adp.dao.UserDAO;
import com.adp.model.DaoFunction;
import com.adp.model.User;

@Component
@Aspect
public class DaoAspect {

	@Autowired(required=true)
	private AspectDAO aspectDAO;
	
	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.updateUser(..) )") 
	public void UserDAOImpl_UpdateUser_Aspect()
	{	}
	

	
	@AfterReturning(  value="UserDAOImpl_UpdateUser_Aspect()" , returning="returnValue" )
	public void UserDAOImpl_UpdateUser_Aspect(JoinPoint joinPoint , Object returnValue)
	{
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session=attr.getRequest().getSession(true);
		User user = (User) session.getAttribute("user");

		String daoFunctionUrl = joinPoint.getSignature().getName();
		DaoFunction daoFunction = aspectDAO.getDaoFunction( daoFunctionUrl );//获取DaoFunction对象
		//System.out.println(returnValue);
		//对修改过的DaoFunctionLog表进行一些字段的插入.....
		
		aspectDAO.addDaoFunctionLog(user, daoFunction);
		
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
