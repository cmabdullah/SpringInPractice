package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Cat {
	private int id = 0;
	private String speec= "Dont feel like" ;
	@Autowired
	public void setId(@Value("1234")int id) {
		this.id = id;
	}
	@Autowired
	public void setSpeec(@Value("How are You")String speec) {
		this.speec = speec;
	}
	public void speek() {
		System.out.println(id+": "+speec);
	}
	
	

}
