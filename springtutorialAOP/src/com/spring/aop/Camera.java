package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera {

	public void snap(){
		System.out.println("SNAP");
	}
	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer"+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
}
