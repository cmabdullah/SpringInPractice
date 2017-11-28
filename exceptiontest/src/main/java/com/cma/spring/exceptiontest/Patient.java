package com.cma.spring.exceptiontest;

public class Patient {
	
	private int id;
	private String name;
	
		public Patient() {
		
	}
	
	public Patient(int id, String name) {
	//super();
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




	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}




	public void setName(String name) {
		this.name = name;
	}




	public void speak(){
		System.out.println("Help me");
	}

}
