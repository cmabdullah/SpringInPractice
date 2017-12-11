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
		// Patient patient2 = (Patient)context.getBean("patient");
   // patient.speak();
   // Address address = (Address)context.getBean("address");
		//patient1.setName("Rafid");
		//Address address2 = (Address)context.getBean("address2");
		System.out.println( patient );
		//System.out.println( address2 );
		//System.out.println( address );
		for (String name:patient.getEmargencyContactNumber()){
			System.out.println(name);
		}
    ((ClassPathXmlApplicationContext)context).close();
    }
}
