package com.spring.aop;

public interface ICamera {

	void snap();
	
	void snap(int exposer);
	String snap(String exposer) ;
	void snapNightTime();
	void snapCar(Car car);
}