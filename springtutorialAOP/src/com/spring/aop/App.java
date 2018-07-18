 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
SNAP
*********** after advice running****************
SNAP!! with exposer : 2000
SNAP!! with exposer : 1.8
*********** Before Demo****************
exposure : 500 ,aperture : 1.800000
SNAP!! with exposer : 500 apature1.8
SNAP!! with Photo name : Hi Cm its your campus 
SNAP!! Night mode .... 
Car engine started......
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");

		camera.snap();
		camera.snap(2000);
		camera.snap(1.8);
		camera.snap(500, 1.8);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();

		
		Car car = (Car) context.getBean("car");
		car.start();
		camera.snapCar(new Car());
		
		context.close();
	}
}