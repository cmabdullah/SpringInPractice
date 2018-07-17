package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("within( com.spring..*)")
	//@Pointcut("within( com.spring.aop.Camera)")
	public void somePointCut() {
		
	}
	
	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}

}
