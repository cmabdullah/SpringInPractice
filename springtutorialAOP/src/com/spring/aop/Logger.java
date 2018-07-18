package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	@Pointcut("args(exposure ,aperture)")
	public void somePointCut(int exposure ,double aperture) {	
	}
	
	@Before("somePointCut(exposure ,aperture)")
	public void somePointCutPhoto(int exposure ,double aperture) {
		System.out.println("*********** Before Demo****************");
		System.out.printf("exposure : %d ,aperture : %f\n", exposure ,aperture);

	}	

}
