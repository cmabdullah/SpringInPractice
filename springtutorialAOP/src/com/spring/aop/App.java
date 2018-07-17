 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Before advice
SNAP
Before advice
SNAP!! with exposer2000
Before advice
SNAP!! with Photo name : Hi Cm its your campus 
Before advice
Car engine started......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		Car car = (Car) context.getBean("car");
		camera.snap();
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		
		car.start();
		context.close();
	}
}
