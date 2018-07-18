package com.spring.aop;

public interface ICamera {

	public abstract void snap();
	public abstract void snap(int exposer);
	public abstract String snap(String exposer) ;
	public abstract void snapNightTime();
	public abstract void snapCar(Car car);
	public abstract void snap(double d);
	public abstract void snap(int exposure ,double aperture);
}