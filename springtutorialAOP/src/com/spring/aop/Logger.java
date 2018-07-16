package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("execution( void com.spring.aop.Camera.snap(..))")

	public void cameraSnap() {
		
	}

	@Before("cameraSnap()")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}

	
	@After("cameraSnap()")
	public void afterAdvice() {
		System.out.println("After advice......");
	}
	
	// if method is executed normally
	@AfterReturning("cameraSnap()")
	public void afterReturning() {
		System.out.println("After Returning......");
	}	
	
	@AfterThrowing("cameraSnap()")
	public void afterThrowing() {
		System.out.println("After Throwing......");
	}	
	
	
	@Around("cameraSnap()")
	public void aroundAdvice(ProceedingJoinPoint p) {
		System.out.println("Around advice (before)......");
		
		try {
			p.proceed();
		} catch (Throwable e) {
			System.out.println("Around advice ......"+e.getMessage());
		}
		
		System.out.println("Around advice (after)......");
	}	
}
