package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}