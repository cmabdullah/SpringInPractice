package com.cma.spring.exceptiontest;

import org.springframework.stereotype.Component;

@Component("filewriter")
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}