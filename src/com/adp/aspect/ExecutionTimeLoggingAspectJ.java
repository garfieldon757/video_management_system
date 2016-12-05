package com.adp.aspect;

import java.math.BigDecimal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeLoggingAspectJ {
	
	@Pointcut("execution( public void com.adp.service.impl.UserManagerImpl.aopTest(..) )") //com.adp.controller.UserController.controllerFunctionTest(..)public void com.adp.aspect.MyBean.sayHello(..)
			public void controllerFunctionTest(){
//		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	@Before(  "controllerFunctionTest()" )
			public void enableFilters(JoinPoint joinPoint){
		System.out.println("This is aspect ,which runs before UserControllerFunctionTest() !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
//	@Around(" execution(public * *(..)) ")  //public * com.adp.controller.UserController.controllerFunctionTest(..)
//	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
//		
//		long startTime = System.nanoTime();
//		String className = pjp.getTarget().getClass().getCanonicalName();
//		String methodName = pjp.getSignature().getName();
//		
//		Object output = pjp.proceed();
//		long elapsedTime = System.nanoTime() - startTime;
//		System.out.println("Execution of " + className + "#" + methodName + "ended in " +
//									new BigDecimal(elapsedTime).divide(new BigDecimal(1000000)) + 
//									"millionsecounds" ) ;
//		
//		
//		return output;
//		
//	}
	
}
