package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class Lens {
	
	@Deprecated
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
