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
		Patient patient1 = (Patient)context.getBean("patient");
		Patient patient2 = (Patient)context.getBean("patient");
   // patient.speak();
   // Address address = (Address)context.getBean("address");
		patient1.setName("Rafid");
		System.out.println( patient2 );
		//System.out.println( address );
    ((ClassPathXmlApplicationContext)context).close();
    }
}
