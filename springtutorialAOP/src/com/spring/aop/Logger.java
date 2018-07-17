package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {
	//@Pointcut("within(@org.springframework.stereotype.Component com.spring..*)")
	
	//only for Deprecated annotation
	//@Pointcut("within(@Deprecated com.spring..*)")
	
	// point all Component annotation.....


	
//	@Pointcut("within(@org.springframework.stereotype.Component com.spring..*)")
//	//@Pointcut("within( com.spring.aop.Camera)")
//	public void somePointCut() {	
//	}
//	
//	@Before("somePointCut()")
//	public void somePointCutPhoto() {
//		System.out.println("*********** Before Demo****************");
//	}

	
	
//	@Pointcut("@target(org.springframework.stereotype.Component)")
//	public void somePointCut() {	
//	}
//
//	@Before("somePointCut()")
//	public void somePointCutPhoto() {
//		System.out.println("*********** Before Demo****************");
//	}	

	
	
//	@Pointcut("@annotation(java.lang.Deprecated)")
//	public void somePointCut() {	
//	}
//
//	@Before("somePointCut()")
//	public void somePointCutPhoto() {
//		System.out.println("*********** Before Demo****************");
//	}	
	
	
	@Pointcut("@args(org.springframework.stereotype.Component)")
	public void somePointCut() {	
	}

	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}	

}
