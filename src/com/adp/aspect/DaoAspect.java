package com.adp.aspect;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.adp.dao.UserDAO;
import com.adp.model.Function;
import com.adp.model.TableField;
import com.adp.model.User;

@Component
@Aspect
public class DaoAspect {

	@Autowired(required=true)
	private AspectDAO aspectDAO;
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.*(..) )") 
	public void Dao_Aspect()
	{	}
	
	@Around(  value="Dao_Aspect()" )
	public Object Dao_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
		
		Object result = joinPoint.proceed();
		
		now = new Timestamp(System.currentTimeMillis()); 
		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session=attr.getRequest().getSession(true);
		User user = (User) session.getAttribute("user");

		String functionUrl = joinPoint.getSignature().getName();
		Function function = aspectDAO.getFunction( functionUrl );//获取DaoFunction对象
		aspectDAO.addFunctionLog( dateTimeStart, dateTimeEnd, user, function);
		
		return result;
	}	
	
	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.updateUser(..) )") 
	public void UpdateUser_Dao_Aspect()
	{	}
	
	@Around(  value="UpdateUser_Dao_Aspect()" )
	public Object UpdateUser_Dao_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
	{
		final User oldUser = (User) joinPoint.getArgs()[0];
		Object result = joinPoint.proceed();
		final User newUser = userDAO.getUserByID( oldUser.getUserID() );
		
		ArrayList<String> updateFieldList = new ArrayList<String>(){{ 
			add("email"); add("sex"); add("userName"); add("userPassword"); add("roleID"); add("applyStatus");  }};
		ArrayList<String> oldValueList = new ArrayList<String>(){{
			add(oldUser.getEmail()); add(oldUser.getSex()); add(oldUser.getUserName()); add(oldUser.getUserPassword()); 
			add(oldUser.getRole().getRoleID().toString()); add( oldUser.getApply_status().toString().toString() );
		}};
		ArrayList<String> newValueList = new ArrayList<String>(){{
			add(newUser.getEmail()); add(newUser.getSex()); add(newUser.getUserName()); add(newUser.getUserPassword()); 
			add(newUser.getRole().getRoleID().toString()); add( newUser.getApply_status().toString().toString() );
		}};
		
		//向DaoFunctionUpdateDetail表中插入
		for(int i = 0 ; i < updateFieldList.size() ; i++){
			
			aspectDAO.addDaoFunctionUpdateDetail(n, updateFieldList.get(i), oldValueList.get(i), newValueList.get(i) );
		}
		
		return result;
	}

//	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.updateUserRole(..) )") 
//	public void UpdateUserRole_Dao_Aspect()
//	{	}
//	
//	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.updateAuthorizationList(..) )") 
//	public void UpdateAuthorizationList_Dao_Aspect()
//	{	}

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
