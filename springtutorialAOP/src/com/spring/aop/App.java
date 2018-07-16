 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
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
		Camera camera = (Camera)context.getBean("camera");
		try {
			camera.snap();
		} catch (Exception e) {
			System.out.println(e.getMessage()  );
		}
		context.close();
	}
}
