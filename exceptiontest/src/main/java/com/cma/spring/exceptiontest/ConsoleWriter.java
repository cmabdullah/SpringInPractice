package com.cma.spring.exceptiontest;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements LogWriter {

	public void write(String text) {
		System.out.println("From console Writer : "+text);
	}
}
