package com.cma.spring.exceptiontest;

import java.util.List;

public class Patient {
	
	private int id;
	private String name;
	
	private EmargencyContact criticalContact;
	
	private List<EmargencyContact> emargencyContacts;
	
	public Patient() {
		
	}
		

	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	
	
	public EmargencyContact getCriticalContact() {
		return criticalContact;
	}


	public void setCriticalContact(EmargencyContact criticalContact) {
		this.criticalContact = criticalContact;
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


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", criticalContact="
				+ criticalContact + "]";
	}


	public List<EmargencyContact> getEmargencyContacts() {
		return emargencyContacts;
	}


	public void setEmargencyContacts(List<EmargencyContact> emargencyContacts) {
		this.emargencyContacts = emargencyContacts;
	}


}
