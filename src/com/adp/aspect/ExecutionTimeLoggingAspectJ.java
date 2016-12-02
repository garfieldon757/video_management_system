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
	
//	@Pointcut(" execution( com.adp.aspect.MyBean.sayHello(..)  ) ") //* com.adp.controller.UserController.controllerFunctionTest(..)    com.adp.aspect.MyBean.sayHello(..)
//	protected void controllerFunctionTest(){
//		
//	}
//	
//	@Before(  "controllerFunctionTest()" )
//	public void enableFilters(JoinPoint joinPoint){
//		System.out.println("This is aspect ,which runs after UserControllerFunctionTest() !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	}
	
	@Around(" execution(public void com.adp.aspect.MyBean.sayHello(..) ) ")  //execution(public * *(..))
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.nanoTime();
		String className = pjp.getTarget().getClass().getCanonicalName();
		String methodName = pjp.getSignature().getName();
		
		Object output = pjp.proceed();
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Execution of " + className + "#" + methodName + "ended in " +
									new BigDecimal(elapsedTime).divide(new BigDecimal(1000000)) + 
									"millionsecounds" ) ;
		
		
		return output;
		
	}
	
}
