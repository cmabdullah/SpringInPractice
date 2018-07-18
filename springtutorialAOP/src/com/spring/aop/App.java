 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Machine Starting.....
Running....
Blending........
Completed....
Machine Starting.....
Running....
Fan is activate at level : 5
Completed....
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		IBlender blender = (IBlender)context.getBean("blender");
		
		((IMachine)blender).start();
		blender.blend();
		
		IFan fan = (IFan)context.getBean("fan");
		((IMachine)fan).start();
		fan.activate(5);
		
		context.close();
	}
}