package com.cma.spring.exceptiontest;

import java.util.ArrayList;
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
    		//insert notice
    		
    		Notice notice1 = new Notice(27,"mainul1", "mainul@gmail.com", "hi mainul How are you?");
    		Notice notice2 = new Notice(36,"mainul2", "mainul@gmail.com", "hi mainul How are you?");
    		Notice notice3 = new Notice(35,"mainul3", "mainul@gmail.com", "hi mainul How are you?");
    		Notice notice4 = new Notice(34,"mainul4", "mainul@gmail.com", "hi mainul How are you?");
    		//noticesDAO.create(notice1);
    		//noticesDAO.update(notice1);
    		
    		List<Notice> noticeList = new ArrayList<Notice>();
    		noticeList.add(notice1);
    		noticeList.add(notice2);
    		noticeList.add(notice3);
    		noticeList.add(notice4);
    		
    		noticesDAO.create(noticeList);
    		
    		//noticesDAO.delete(2); //delete method called
    		List<Notice> notices = noticesDAO.getNotices();
    		
        	for(Notice notice: notices) {
        		System.out.println(notice);
        	}

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
