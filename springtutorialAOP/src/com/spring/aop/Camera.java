package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
//public class Camera implements PhotoSnapper{
public class Camera implements PhotoSnapper, ICamera{
//	public Camera() {
//		System.out.println("From Camera constructor.......");
//	}
	
	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	public void snap() throws Exception{
		System.out.println("SNAP");
		throw new Exception("by by....");
	}

}
