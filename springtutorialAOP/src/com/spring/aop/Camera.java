package com.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}

	@Override
	public void snap(double exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
		
	}

	@Override
	public void snap(int i, double d) {
		System.out.println("SNAP!! with exposer : "+i+" apature"+d);
		
	}
}
