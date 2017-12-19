package com.cma.spring.exceptiontest;

import java.util.List;

public class Patient {
	
	private int id;
	private String name;
	
	private List<EmargencyContact> emargencyContacts;
	
	public Patient() {
		
	}
		

	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public List<EmargencyContact> getEmargencyContacts() {
		return emargencyContacts;
	}


	public void setEmargencyContacts(List<EmargencyContact> emargencyContacts) {
		this.emargencyContacts = emargencyContacts;
	}


}
