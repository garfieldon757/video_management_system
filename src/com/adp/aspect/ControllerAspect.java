package com.adp.aspect;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.adp.dao.AspectDAO;
import com.adp.model.Function;
import com.adp.model.User;

@Component
@Aspect
public class ControllerAspect {

	@Autowired(required=true)
	private AspectDAO aspectDAO;
	
	@Pointcut("execution( public * com.adp.controller.UserController.*(..) )") 
	public void Controller_Aspect()
	{	}
	
	@Around(  value="Controller_Aspect()")
	public Object Controller_Aspect(ProceedingJoinPoint joinPoint) throws Throwable
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
		Function function = aspectDAO.getFunction(functionUrl); //获取ControllerFunction对象

		aspectDAO.addFunctionLog(dateTimeStart, dateTimeEnd, user, function);
		return result;
	}	

	
}
