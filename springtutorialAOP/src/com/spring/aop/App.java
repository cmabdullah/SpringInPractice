 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
*********** Within Demo
*********** Target Demo
*********** This Demo
SNAP
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		camera.snap();

		context.close();
	}
}
