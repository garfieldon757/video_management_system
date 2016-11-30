package com.adp.aspect;

import org.springframework.stereotype.Component;

@Component
public class MyOtherBean {

	public void sayHelloDelay() throws InterruptedException{
		Thread.sleep(1000);
		System.out.println("hello(delay)...");
	}
}
