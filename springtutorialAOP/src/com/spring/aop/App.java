 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
About to take photo
Doing something related to camera......
SNAP
About to take photo
Doing something related to camera......
SNAP!! with exposer2000
About to take photo
Doing something related to camera......
SNAP!! with Photo name : Hi Cm its your campus 
About to take photo
Doing something related to camera......
SNAP!! Night mode .... 
Doing something related to camera......
Zoom lens : 3
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();
		lens.zoom(3);
		context.close();
	}
}
