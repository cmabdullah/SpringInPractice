package com.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("name", user.getName());
		
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority()); 

		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		return jdbc.update("insert into users (username,password ,name, email, authority, enabled) values (:username,:password,:name,:email,:authority,:enabled)", params) == 1;
		
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
	}
	public List<User> getAllUsers() {
		return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
	}
}
