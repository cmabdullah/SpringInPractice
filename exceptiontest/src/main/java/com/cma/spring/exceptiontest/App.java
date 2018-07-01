package com.cma.spring.exceptiontest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	NoticesDAO noticesDAO = (NoticesDAO) context.getBean("noticesDAO");
    	List<Notice> notices = noticesDAO.getNotice();
    	for(Notice notice: notices) {
    		System.out.println(notice);
    	}
    ((ClassPathXmlApplicationContext)context).close();
    }
}
