package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Before("execution( void com.spring.aop.Camera.snap())")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}
}
