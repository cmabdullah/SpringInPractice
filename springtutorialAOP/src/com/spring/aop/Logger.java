package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	//@Pointcut("execution( void com.spring.aop.Camera.snap(..))")
	//@Pointcut("execution( *  com.spring.aop.Camera.snap(..))")
	//@Pointcut("execution( *  com.spring.aop.Camera.*(..))")
	@Pointcut("execution( *  com.spring.aop.Camera.*(..))")
	//@Pointcut("execution( *  *.*(..))")
	public void cameraSnap() {
		
	}
	@Pointcut("execution( *  *.*(..))")
	public void cameraActivity() {
		
	}
	
	//@Before("execution( void com.spring.aop.Camera.snap())")
	@Before("cameraSnap()")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}
	@Before("cameraActivity()")
	public void cameraRelatedActivity() {
		System.out.println("Doing something related to camera......");
	}
}
