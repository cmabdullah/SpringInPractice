package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	@Pointcut("target(com.spring.aop.Camera)")
	public void somePointCut() {	
	}
	
	@Before("somePointCut()")
	public void somePointCutPhoto(JoinPoint jp) {
		System.out.println("*********** Before Demo****************");
		
		for (Object ob : jp.getArgs()) {
			System.out.println("Args :" + ob);
		}
	}	

}
