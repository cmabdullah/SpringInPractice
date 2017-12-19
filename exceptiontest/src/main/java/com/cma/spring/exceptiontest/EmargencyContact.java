package com.cma.spring.exceptiontest;

public class EmargencyContact {
	private String name;
	private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "EmargencyContact [name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
	}
}
