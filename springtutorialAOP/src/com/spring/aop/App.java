 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Getting object of camera class com.sun.proxy.$Proxy11
Camera is instance of camera class : true
Around advice (before)......
About to take photo
SNAP
Around advice ......by by....
Around advice (after)......
After advice......
After Returning......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		//Object camera = context.getBean("camera");
		System.out.println("Getting object of camera "+camera.getClass());
		System.out.println("Camera is instance of ICamera class : "+(camera instanceof ICamera));
		
		
		try {
			camera.snap();
		} catch (Exception e) {
			System.out.println(e.getMessage()  );
		}
		context.close();
	}
}
