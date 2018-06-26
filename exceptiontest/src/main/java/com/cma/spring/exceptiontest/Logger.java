package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Logger {
	//@Autowired //variable level auto wiring
	private ConsoleWriter consoleWriter;
	//@Autowired 
	private LogWriter fileWriter;

	public LogWriter getConsoleWriter() {
		return consoleWriter;
	}
	@Autowired //method level auto wiring
	@Qualifier("consolewriter")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() {
		return fileWriter;
	}
	@Autowired
	@Qualifier("filewriter")
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
}
