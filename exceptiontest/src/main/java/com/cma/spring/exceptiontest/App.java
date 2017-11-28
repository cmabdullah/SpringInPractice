package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
    patient.speak();
		System.out.println( patient );
    ((ClassPathXmlApplicationContext)context).close();
    }
}