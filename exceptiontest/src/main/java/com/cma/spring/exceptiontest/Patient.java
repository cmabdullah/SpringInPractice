package com.cma.spring.exceptiontest;

import java.util.List;

public class Patient {
	
	private int id;
	private String name;
	private List<String> emargencyContactNumber;
	
	public Patient() {
		
	}
		
	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
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
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}
	public List<String> getEmargencyContactNumber() {
		return emargencyContactNumber;
	}
	public void setEmargencyContactNumber(List<String> emargencyContactNumber) {
		this.emargencyContactNumber = emargencyContactNumber;
	}

	public void speak(){
		System.out.println("Help me");
	}
}
