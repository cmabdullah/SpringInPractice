package com.cma.spring.exceptiontest;

import java.util.HashMap;
import java.util.Map;

public class ContactBook {
	private Map<String , EmargencyContact> contacts = new HashMap <String, EmargencyContact>();

	public Map<String, EmargencyContact> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, EmargencyContact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, EmargencyContact> contacts : contacts.entrySet() ){
			sb.append(contacts.toString()+"\n");
		}
		return sb.toString();
	}

}
