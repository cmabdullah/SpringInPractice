package com.spring.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.web.dao.User;
import com.spring.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml",
		"classpath:com/spring/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	@Autowired
	private UserDao userDao;
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new  JdbcTemplate(dataSource);
		jdbc.execute("delete from notices");
		jdbc.execute("delete from users");
		
	}
	@Test
	public void testCreateUser() {
		//assertEquals("Dummy test", 1, 1);
		
		User user = new User("abiabdullah", "123456789", "cm@gmail.com",true, "ROLE_USER");
		assertTrue("User create should be return true",userDao.create(user));
		
		List<User> users = userDao.getAllUsers(); 
		assertEquals("Number of users shuld be 1", 1, users.size());
		
		assertTrue("User should exist ", userDao.exists(user.getUsername()));
		
		assertEquals("Create user should be identicle to retrive user", user, users.get(0));
		
	}
}
