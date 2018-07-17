 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
*********** Before Demo****************
SNAP
*********** Before Demo****************
SNAP!! with exposer : 2000
*********** Before Demo****************
SNAP!! with Photo name : Hi Cm its your campus 
*********** Before Demo****************
SNAP!! Night mode .... 
*********** Before Demo****************
Zoom lens : 500
*********** Before Demo****************
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();
		lens.zoom(500);
		
		Car car = (Car) context.getBean("car");
		camera.snapCar(car);
		
		context.close();
	}
}