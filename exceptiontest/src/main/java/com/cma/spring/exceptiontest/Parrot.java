package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parrot {
	private int id = 0;
	private String speech= "Dont feel like" ;
	@Autowired
	public void setId(@Value("1234")int id) {
		this.id = id;
	}
	@Autowired
	public void setSpeech(@Value("How are You")String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}

}
