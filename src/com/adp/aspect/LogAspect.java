package com.adp.aspect;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.adp.dao.AspectDAO;
import com.adp.dao.UserDAO;
import com.adp.model.Function;
import com.adp.model.User;

@Component
@Aspect
public class LogAspect {

	@Autowired(required=true)
	private AspectDAO aspectDAO;
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
//	@Pointcut( value="execution( public * com.adp.controller.UserController.*(..))" ) 
//	public void log_controller_Aspect()
//	{	}
//	
//	@Around(  value="log_controller_Aspect()" )
//	public Object log_controller_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
//	{
//		Timestamp now = new Timestamp(System.currentTimeMillis()); 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
//		
//		Object result = joinPoint.proceed();
//		
//		now = new Timestamp(System.currentTimeMillis()); 
//		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		
//		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session=attr.getRequest().getSession(true);
//		User user = (User) session.getAttribute("user");
//
//		String functionUrl = joinPoint.getSignature().getName();
//		String functionType = "controller";
//		Function function = aspectDAO.getFunction( functionUrl , functionType );//获取controllerFunction对象
//		aspectDAO.addFunctionLog( dateTimeStart, dateTimeEnd, user, function);
//		
//		now = new Timestamp(System.currentTimeMillis()); 
//		String test = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		System.out.println("contoller end time -- " + test);
//		
//		return result;
//	}	
//	
//	@Pointcut( value="execution( public * com.adp.service.impl.UserManagerImpl.*(..))" ) 
//	public void log_service_Aspect()
//	{	}
//	
//	@Around(  value="log_service_Aspect()" )
//	public Object log_service_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
//	{
//		Timestamp now = new Timestamp(System.currentTimeMillis()); 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
//		
//		Object result = joinPoint.proceed();
//		
//		now = new Timestamp(System.currentTimeMillis()); 
//		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		
//		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session=attr.getRequest().getSession(true);
//		User user = (User) session.getAttribute("user");
//
//		String functionUrl = joinPoint.getSignature().getName();
//		String functionType = "service";
//		Function function = aspectDAO.getFunction( functionUrl , functionType );//获取serviceFunction对象
//		aspectDAO.addFunctionLog( dateTimeStart, dateTimeEnd, user, function);
//		
//		now = new Timestamp(System.currentTimeMillis()); 
//		String test = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		System.out.println("service end time -- " + test);
//		
//		return result;
//	}	
//	
//	@Pointcut( value=" execution( public * com.adp.dao.impl.UserDAOImpl.*(..)) && !execution( public * com.adp.dao.impl.UserDAOImpl.updateUser(..) ) " ) 
//	public void log_dao_Aspect()
//	{	}
//	
//	@Around(  value="log_dao_Aspect()" )
//	public Object log_dao_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
//	{
//		Timestamp now = new Timestamp(System.currentTimeMillis()); 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
//		
//		Object result = joinPoint.proceed();
//		
//		now = new Timestamp(System.currentTimeMillis()); 
//		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		
//		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session=attr.getRequest().getSession(true);
//		User user = (User) session.getAttribute("user");
//
//		String functionUrl = joinPoint.getSignature().getName();
//		String functionType = "dao";
//		Function function = aspectDAO.getFunction( functionUrl , functionType );//获取DaoFunction对象
//		aspectDAO.addFunctionLog( dateTimeStart, dateTimeEnd, user, function);
//		
//		now = new Timestamp(System.currentTimeMillis()); 
//		String test = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		System.out.println("dao end time -- " + test);
//		
//		return result;
//	}	
//	
//	@Pointcut("execution( public * com.adp.dao.impl.UserDAOImpl.updateUser(..) )") 
//	public void UpdateUser_Dao_Aspect()
//	{	}
//	
//	@Around(  value="UpdateUser_Dao_Aspect()" )
//	public Object UpdateUser_Dao_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
//	{
//		/**********************切入点前代码**************************/
//		Timestamp now = new Timestamp(System.currentTimeMillis()); 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
//		final User oldUser = (User) joinPoint.getArgs()[0];
//		/*************************************************************/
//		
//		Object result = joinPoint.proceed();
//		
//		/**********************切入点后代码**************************/
//		now = new Timestamp(System.currentTimeMillis()); 
//		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
//		final User newUser = userDAO.getUserByID( oldUser.getUserID() );
//		/*************************************************************/
//		
//		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session=attr.getRequest().getSession(true);
//		User user = (User) session.getAttribute("user");
//
//		String functionUrl = joinPoint.getSignature().getName();
//		String functionType = "dao";
//		Function function = aspectDAO.getFunction( functionUrl , functionType );//获取DaoFunction对象
//		int functionLogID = aspectDAO.addFunctionLog( dateTimeStart, dateTimeEnd, user, function);
//		
//		
//		/***********这里针对update函数，将被更新的字段修改前/后的值更新在DaoFunctionUpdateDetail表**********/
//		ArrayList<String> updateFieldList = new ArrayList<String>(){{ 
//			add("email"); add("sex"); add("userName"); add("userPassword"); add("roleID"); add("applyStatus");  }};
//		ArrayList<String> oldValueList = new ArrayList<String>(){{
//			add(oldUser.getEmail()); add(oldUser.getSex()); add(oldUser.getUserName()); add(oldUser.getUserPassword()); 
//			add(oldUser.getRole().getRoleID().toString()); add( oldUser.getApply_status().toString().toString() );
//		}};
//		ArrayList<String> newValueList = new ArrayList<String>(){{
//			add(newUser.getEmail()); add(newUser.getSex()); add(newUser.getUserName()); add(newUser.getUserPassword()); 
//			add(newUser.getRole().getRoleID().toString()); add( newUser.getApply_status().toString().toString() );
//		}};
//		
//		//向DaoFunctionUpdateDetail表中插入
//		for(int i = 0 ; i < updateFieldList.size() ; i++){
//			aspectDAO.addDaoFunctionUpdateDetail( functionLogID , updateFieldList.get(i), oldValueList.get(i), newValueList.get(i) );
//		}
//		/*********************************************************************************************************************/
//		
//		return result;
//	}


	
	
	
}
