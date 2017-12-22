package com.cma.spring.exceptiontest;

public class ConsoleWriter implements LogWriter {

	public void write(String text) {
		System.out.println("From console Writer : "+text);
	}
}
