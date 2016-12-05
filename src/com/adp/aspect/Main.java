package com.adp.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String... args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml" , Main.class);
		MyBean myBean = context.getBean(MyBean.class);
		myBean.sayHello();
	}
}
