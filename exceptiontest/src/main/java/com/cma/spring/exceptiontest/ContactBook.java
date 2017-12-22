package com.cma.spring.exceptiontest;

import java.util.HashMap;
import java.util.Map;

public class ContactBook {
	private Map<String , EmargencyContact> contacts = new HashMap <String, EmargencyContact>();
	private Patient patient;
	public ContactBook(){
		patient = new Patient(10, "Mainul");
	}
	public Map<String, EmargencyContact> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, EmargencyContact> contacts) {
		this.contacts = contacts;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(patient == null ? "Patient is null\n": patient.toString()+"\n");
		for (Map.Entry<String, EmargencyContact> contacts : contacts.entrySet() ){
			sb.append(contacts.toString()+"\n");
		}
		return sb.toString();
	}

}
