package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.web.dao.Notice;
import com.spring.web.dao.NoticesDAO;
@Service("noticesService")
public class NoticesService {
	private NoticesDAO noticesDAO;
	@Autowired
	public void setNoticesDAO(NoticesDAO noticesDAO) {
		this.noticesDAO = noticesDAO;
	}
	public List<Notice> getCurrent(){
		return noticesDAO.getNotices();
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public void create(Notice notice) {
		noticesDAO.create(notice);
		
	}

}
