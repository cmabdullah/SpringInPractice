package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Fan implements IFan {
	/* (non-Javadoc)
	 * @see com.spring.aop.IFan#activate(int)
	 */
	@Override
	public void activate(int level) {
		System.out.println("Fan is activate at level : "+level);	
	}

}
