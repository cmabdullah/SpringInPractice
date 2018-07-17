 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
*********** Before Demo****************
SNAP
*********** Before Demo****************
Zoom lens : 500
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		lens.zoom(500);

		context.close();
	}
}
