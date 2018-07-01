package com.cma.spring.exceptiontest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	NoticesDAO noticesDAO = (NoticesDAO) context.getBean("noticesDAO");
    	/***
    	List<Notice> notices = noticesDAO.getNotice();
    	for(Notice notice: notices) {
    		System.out.println(notice);
    	}
    	**/
    	try {
    		List<Notice> notices = noticesDAO.getNotices();
        	for(Notice notice: notices) {
        		System.out.println(notice);
        	}
        	Notice notice = noticesDAO.getNotice(2);
        	System.out.println("Single Notice : "+notice);
    	}catch(CannotGetJdbcConnectionException e) {
    		System.out.println(e.getMessage());
    		System.out.println(e.getClass());
    	}
    	catch(DataAccessException e) {
    		System.out.println(e.getMessage());
    		System.out.println(e.getClass());
    	}

    ((ClassPathXmlApplicationContext)context).close();
    }
}
