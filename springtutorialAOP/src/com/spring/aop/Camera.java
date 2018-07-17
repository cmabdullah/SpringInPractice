package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	public void snap(){
		System.out.println("SNAP");
	}

}
