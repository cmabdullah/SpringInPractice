package com.cma.spring.exceptiontest;
import javax.annotation.*;
import javax.inject.Inject;
import javax.inject.Named;
public class Logger {

	private ConsoleWriter consoleWriter;
	private LogWriter fileWriter;
	@Inject
	@Named(value="demoWriter2")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	@Inject
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
	@PostConstruct
	public void init() {
		System.out.println("beans created");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("beans destroyed");
	}
}