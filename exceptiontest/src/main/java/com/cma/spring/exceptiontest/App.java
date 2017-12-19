package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {

    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");

			System.out.println(patient);
		for (EmargencyContact name:patient.getEmargencyContacts()){
			System.out.println(name);
		}
    ((ClassPathXmlApplicationContext)context).close();
    }
}
