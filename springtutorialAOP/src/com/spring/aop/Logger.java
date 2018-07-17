package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	//@Pointcut("bean(camera)")
	@Pointcut("bean(*e*)")
	public void somePointCut() {	
	}

	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}	

}
