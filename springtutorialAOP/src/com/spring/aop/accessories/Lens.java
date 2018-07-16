package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
public class Lens {
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
