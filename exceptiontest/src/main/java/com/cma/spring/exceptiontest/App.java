package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Parrot parrot = (Parrot) context.getBean("parrot");
    	parrot.speek();
    ((ClassPathXmlApplicationContext)context).close();
    }
}
