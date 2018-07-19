package com.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.User;
import com.spring.web.dao.UserDao;
@Service("usersService")
public class UsersService {
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void create(User user) {
		userDao.create(user);	
	}
}
