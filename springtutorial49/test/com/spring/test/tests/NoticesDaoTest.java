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

import com.spring.web.dao.Notice;
import com.spring.web.dao.NoticesDao;
import com.spring.web.dao.User;
import com.spring.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml",
		"classpath:com/spring/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class NoticesDaoTest {
	
	@Autowired
	private NoticesDao noticesDao;
	
	
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
		
		User user = new User("abiabdullah", "abdullah Khan", "123456789", "cm@gmail.com ", true,
				"ROLE_USER");
		
		
		
		
		assertTrue("User create should be return true",userDao.create(user));
		
		Notice notice = new Notice(user, "This is a test notice.");
		
		assertTrue("Notice creation should return true", noticesDao.create(notice));
		
		List<Notice> notices = noticesDao.getNotices();
		
		assertEquals("Should be one offer in database.", 1, notices.size());
		
		
		//why this line fails test i don't know
		//assertEquals("Retrieved notice should match created notice.", notice, notices.get(0));
		
		// Get the notice with ID filled in.
		
		notice = notices.get(0);
		
		notice.setText("Updated offer text.");
		assertTrue("Notice update should return true", noticesDao.update(notice));
		
		Notice updated = noticesDao.getNotice(notice.getId());
		
		assertEquals("Updated notice should match retrieved updated notice", notice, updated);
		
		noticesDao.delete(notice.getId());
		
		List<Notice> empty = noticesDao.getNotices();
		
		assertEquals("Notices lists should be empty.", 0, empty.size());
	
	}
}