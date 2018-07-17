package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("within( com.spring.aop.*)")
//@Pointcut("within( com.spring.aop.Camera)")
	public void withinDemo() {
		
	}

	@Pointcut("target( com.spring.aop.Camera)")
	public void targetDemo() {
			
	}
	@Pointcut("this( com.spring.aop.ICamera)")
	public void thisDemo() {
			
	}	
	@Before("withinDemo()")
	public void aboutToTakePhoto() {
		System.out.println("*********** Within Demo");
	}
	@Before("targetDemo()")
	public void targetBeforeDemo() {
		System.out.println("*********** Target Demo");
	}

	@Before("thisDemo()")
	public void thisBeforeDemo() {
		System.out.println("*********** This Demo");
	}
}
