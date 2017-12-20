package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {

    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	ContactBook contacts = (ContactBook)context.getBean("contactbook");

			System.out.println(contacts);

    ((ClassPathXmlApplicationContext)context).close();
    }
}
