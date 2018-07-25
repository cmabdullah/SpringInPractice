# Lacture 119-120
## Objective : Adding Log4j Logging and Resolving Logging Conflict

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# Location src/

# log4j.properties

```properties
	log4j.rootLogger=DEBUG, CONSOLE
	log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
	log4j.appender.CONSOLE.layout=org.apache.log4j.SimpleLayout
```

> Go to Run Configurations => Tomcat => Arguments => -Dlog4j.debug

# package name com.spring.web.controller


# Dependencies
	  	<dependency>
	  		<groupId>log4j</groupId>
	  		<artifactId>log4j</artifactId>
	  		<version>1.2.14</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.slf4j</groupId>
	  		<artifactId>slf4j-api</artifactId>
	  		<version>1.7.6</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.slf4j</groupId>
	  		<artifactId>slf4j-jcl</artifactId>
	  		<version>1.7.6</version>
	  	</dependency>

#### exclusions tiles Dependency
		  	<dependency>
		  		<groupId>org.apache.tiles</groupId>
		  		<artifactId>tiles-extras</artifactId>
		  		<version>3.0.5</version>
		  		<exclusions>
		  			<exclusion>
		  				<groupId>org.slf4j</groupId>
		  				<artifactId>jcl-over-slf4j</artifactId>
		  			</exclusion>
		  		</exclusions>
		  	</dependency>


### HomeController.java

```java
package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}	
}
```

### NoticeController.java

```java
package com.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
	
	NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	//http://localhost:8081/notices/test?id=100
	//Getting URL Parameters
	@RequestMapping("/test")
	public String showTest(Model model , @RequestParam("id")String id) {
		System.out.println("ID is : "+id);
		return "home";
	}	
	
//	@ExceptionHandler(DataAccessException.class)
//	public String handleDatabaseException(DataAccessException ex) {
//		return "error";
//	}
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		
		//if throw exception it will be handled by Database error Handler
		//noticesService.throwTextException();
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	@RequestMapping("/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());
		return "createnotice";
	}
	//notice beans will inject automatically
	@RequestMapping(value = "/docreate", method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Notice notice, BindingResult result) {
		System.out.println(notice);
		
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "createnotice";
		}
		noticesService.create(notice);
//		else {
//			System.out.println("Form is validet");
//		}
		return "noticecreated";
	}
}
```


### ErrorHandler.java

```java
package com.spring.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedException ex) {
		return "denied";
	}
}
```

### LoginController.java

```java
package com.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web.dao.User;
import com.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	
	UsersService usersService;
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/denied")
	public String accessDenied() {
		return "denied";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		//user.setAuthority("user");

		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
	
		try {
			usersService.create(user);
		}catch(DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
			return "newaccount";
		}
		return "accountcreated";
	}	
}
```

# Package name com.spring.web.dao

### Notice.java

```java
package com.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//this package created by us for valid email annotation
import com.spring.web.validation.ValidEmail;

public class Notice {
	private int id;
	@Size(min=4  ,max=100, message="Name must be between 5 to 100")
	private String name;
	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
	// ValideEmail Annotation created by us, see import com.spring.web.validation.ValidEmail; package
	@ValidEmail
	private String email;
	@Size(min=20  ,max=250, message="text must be between 20 to 250")
	private String text;
	
	public Notice() {

	}	
	
	public Notice(String name, String email, String text) {
		this.name = name;
		this.email = email;
		this.text = text;
	}	
	
	public Notice(int id, String name, String email, String text) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
	}
	
}
```

### NoticesDAO.java

```java
package com.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
	public NoticesDAO() {//constructor added for debugg purposes
		System.out.println("Beans are configured");
	}	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<Notice> getNotices(){
		
		return jdbc.query("select * from notices", new RowMapper<Notice>() {
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setEmail(rs.getString("email"));
				notice.setText(rs.getString("text"));
				return notice;
			}
		});
	}
	
	public boolean delete(int id) { // delete method
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.update("delete from notices where id = :id", params ) == 1 ; // return true if success
	}
	
	@Transactional
	public int[] create(List<Notice> notices) {
		SqlParameterSource[] params =  SqlParameterSourceUtils.createBatch(notices.toArray());
		return jdbc.batchUpdate("insert into notices (id,name , email, text) values (:id,:name,:email,:text)", params);		
	}

	public boolean create(Notice notice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		return jdbc.update("insert into notices (name , email, text) values (:name,:email,:text)", params) == 1;
	}
	
	public boolean update(Notice notice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		return jdbc.update("update notices set name=:name,email=:email,text=:text where id=:id", params) == 1;
	}
	
	public Notice getNotice(int id){
		//কোন অর্ডার ফলো করতে হয় না।
		//MapSqlParameterSource params = new MapSqlParameterSource("name", "cm");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		//return jdbc.query("select * from notices where name = 'cm'", new RowMapper<Notice>() {
		return jdbc.queryForObject("select * from notices where id = :id", params, new RowMapper<Notice>() {
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setEmail(rs.getString("email"));
				notice.setText(rs.getString("text"));
				return notice;// return single object
			}
		});
	}
}
```


### User.java

```java
package com.spring.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.spring.web.validation.ValidEmail;

public class User {
	@NotBlank
	@Size(min=4  ,max=15)
	@Pattern(regexp="^\\w{4,}$")
	private String username;
	@NotBlank
	@Size(min=8  ,max=15)
	@Pattern(regexp="^\\S+$")	
	private String password;
	
	@ValidEmail
	private String email;
	private boolean enabled;
	private String authority;
	
	public User() {
		
	}

	public User(String username, String password, String email, boolean enabled, String authority) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
```

### UserDao.java

```java
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
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority()); 

		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
	}
	public List<User> getAllUsers() {
		return jdbc.query("select * from users , authorities where users.username = authorities.username", BeanPropertyRowMapper.newInstance(User.class));
	}
}
```


## package name com.spring.web.service

### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

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
	//create method for insert value 
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public void create(Notice notice) {
		noticesDAO.create(notice);
	}
}
```



### UsersService.java

```java
package com.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;
import java.util.List;


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
	public boolean exists(String username) {
		return userDao.exists(username);
	}
	//method level security enabled
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}	
}
```


### ValidEmail.java

#### under com.spring.web.validation package

```java
package com.spring.web.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {com.spring.web.validation.ValidEmailImpl.class })
public @interface ValidEmail {

	String message() default "Does not seem to be a valid email address";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	int min() default 5;

}

```
### ValidEmailImpl.java

```java
package com.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail,String>{
	int min ;
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		min = constraintAnnotation.min();	
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email.length() < min) {
			return false;
		}
		if (!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}
		return true;
	}
}
```

# package com.spring.web.message

## Message.properties

```properties
	Size.user.username = User Name must be between 4 to 15 long.....
	NotBlank.user.username = username cannot be blank
	Pattern.user.username = Username only consist number letter underscore

	NotBlank.user.password = Password cannot be blank.....
	Size.user.password = Password must be between 8 to 15 long
	Pattern.user.password = Password cannot contain any space

	ValidEmail.user.email= this does not seems valid email

	UnmatchedPasswords.user.password = Passwords must match.


	DuplicateKey.user.username = this username already exist, please choose different username
```


### home.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br>
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>

<!--</b>-->
</b>
```


### createnotice.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<div class="col-md-6 col-md-offset-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="notice">
		<fieldset>
			<!-- Form Name -->
			<legend>Create Notice</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="name">Name :</label>
				<div class="col-md-4">
					<!-- Path must be equal to input name -->
					<sf:input id="name" path="name" name="name" type="text"
						placeholder="Enter your name" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="name" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email :</label>
				<div class="col-md-4">
					<sf:input id="email" name="email" path="email" type="text"
						placeholder="Enter your email" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Notice :</label>
				<div class="col-md-4">
					<sf:textarea class="form-control" path="text" id="text" name="text"></sf:textarea>
					<!-- Show error message into view -->
					<sf:errors path="text" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Create
						Notice</button>
				</div>
			</div>
		</fieldset>
	</sf:form>
</div>
```

### notices.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
 -->
<table class="table table-striped">
	<tr>
		<th scope="col">ID</th>
		<th scope="col">Name</th>
		<th scope="col">Email</th>
		<th scope="col">Notice</th>
	</tr>
	<c:forEach var="notice" items="${notices}">
		<tr>
			<td><c:out value="${notice.id}"></c:out></td>
			<td><c:out value="${notice.name}"></c:out></td>
			<td><c:out value="${notice.email}"></c:out></td>
			<td><c:out value="${notice.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>
```

### noticecreated.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a href="${pageContext.request.contextPath}/notices">Current notices</a><br>

<!--</b>-->
</b>
```

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(j_username).focus();
	});
</script>
<h3>Login with Username and Password</h3>
<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal"
		action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<fieldset>
			<!-- Form Name -->
			<legend>Login</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_username">Username
					:</label>
				<div class="col-md-4">
					<input id="j_username" name="j_username" type="text" placeholder=""
						class="form-control input-md">
				</div>
			</div>
			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_password">Password
					:</label>
				<div class="col-md-4">
					<input id="j_password" name="j_password" type="password"
						placeholder="" class="form-control input-md">
				</div>
			</div>
			<div class="alert-danger">
				<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
			</div>
			<!-- Checkbox input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_password">Remember
					me :</label> <input id="_spring_security_remember_me"
					name="_spring_security_remember_me" type="checkbox"
					checked="checked">
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</fieldset>
	</form>
	<p>
		<a href="<c:url value='/newaccount'/>">create new Account</a>
	</p>
</div>
```



### newaccount.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<div class="col-md-6 col-md-offset-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<fieldset>
			<!-- Form Name -->
			<legend>Create User</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username">Username
					: </label>
				<div class="col-md-4">
					<!-- Path must be equal to input name -->
					<sf:input id="username" path="username" name="username" type="text"
						placeholder="Enter your username" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- email input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email :</label>
				<div class="col-md-4">
					<sf:input id="email" name="email" path="email" type="text"
						placeholder="Enter your email" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Password
					:</label>
				<div class="col-md-4">
					<sf:input id="password" name="password" path="password"
						type="password" class="form-control input-md" />
					<sf:errors path="password" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!--Conform Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="confirmpassword">Confirm
					Password :</label>
				<div class="col-md-4">
					<input id="confirmpassword" name="confirmpassword" type="password"
						class="form-control input-md" />
				</div>
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Create
						User</button>
				</div>
			</div>
		</fieldset>
	</sf:form>
</div>
<script>
	var password1 = document.getElementById('password');
	var password2 = document.getElementById('confirmpassword');
	var checkPasswordValidity = function() {
		if (password1.value != password2.value) {
			password1
					.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
		} else {
			password1.setCustomValidity('');
		}
	};
	password1.addEventListener('change', checkPasswordValidity, false);
	password2.addEventListener('change', checkPasswordValidity, false);
	/***commandName="user"***/
	var form = document.getElementById('user');
	form.addEventListener('submit', function() {
		checkPasswordValidity();
		if (!this.checkValidity()) {
			event.preventDefault();
			//Implement you own means of displaying error messages to the user here.
			password1.focus();
		}
	}, false);
</script>
```

### accountcreated.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Your account has been created.......

<!--</b>-->
</b>
```

### loggedout.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
You have logged out........

<!--</b>-->
</b>
```


### admin.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped">
	<tr>
		<th scope="col">Username</th>
		<th scope="col">Email</th>
		<th scope="col">Authority</th>
		<th scope="col">Enabled</th>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr>
			<td><c:out value="${user.username}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
			<td><c:out value="${user.authority}"></c:out></td>
			<td><c:out value="${user.enabled}"></c:out></td>
		</tr>
	</c:forEach>
</table>
```

### denied.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Access denied....

</br>
```

### error.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
a database error occured, please check your connection or try again later

</br>
```


### default.jsp
#### Location : WEB-INF/templates

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- apache tiles taglib -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><tiles:getAsString name="title"></tiles:getAsString></title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/static/lib/bootstrap/css/bootstrap.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='/static/css/notice-main.css'/>" rel="stylesheet">
	<script src="<c:url value='/static/js/jquery-3.3.1.slim.min.js'/>"></script>
	<script src="<c:url value='/static/lib/bootstrap/js/bootstrap.min.js'/>"></script>
  </head>
  <body>
		<!-- 	<h1>hello from tiles</h1> -->
	<div>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<main role="main" class="container">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
    </main><!-- /.container -->
	<div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>
```

### header.jsp
#### Location : WEB-INF/tiles

```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<a class="navbar-brand" href="#">Notices</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<!-- Header file in -->
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="<c:url value='/'/>">Home
			</a></li>
		</ul>
		<ul class = "nav navbar-nav navbar-right">	
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/login'/>">Log In</a> </li>
			</sec:authorize>					
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"> <a  class="nav-link"  href="<c:url value='/j_spring_security_logout'/>">Log Out</a> </li>
			</sec:authorize>
		</ul>
	</div>
</nav>

<!--</br>-->
</br>
```

### content.jsp
#### Location : WEB-INF/tiles

```java
<div class="starter-template">
	<h1>Bootstrap starter template</h1>
	<p class="lead">
		Use this document as a way to quickly start any new project.<br>
		All you get is this text and a mostly barebones HTML document.
	</p>
</div>
```

### footer.jsp
#### Location : WEB-INF/tiles

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```

### service-context.xml

#### Under com.spring.web.config Package

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.service"></context:component-scan>
</beans>

```

### dao-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jee="http://www.springframework.org/schema/jee"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">

	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>


	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!--tx active-->
	<tx:annotation-driven />
</beans>
```


### notices-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:component-scan base-package="com.spring.controller"></context:component-scan>
	<mvc:annotation-driven></mvc:annotation-driven>

	<!--Enable bootstrap-->
		<mvc:resources location="/resources/" mapping="/static/**" />

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
	</property>
	</bean>

	<!--Tiles view Resolver-->
	<bean id="tilesViewResolver"
		class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
	</bean>
	
	<bean id="tilesConfigurer"
		class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
	<property name="definitions">
		<list>
			<value>/WEB-INF/layout/default.xml</value>
		</list>
	</property>
	</bean>





</beans>

```

### security-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-3.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


	<security:authentication-manager>

		
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource"
				authorities-by-username-query="SELECT * FROM authorities where binary username = ?"
				users-by-username-query="SELECT * FROM users where binary username = ?"
				id="jdbcUserService" />


			<!-- taking reference from passwordEncoder -->
			<security:password-encoder ref="passwordEncoder"></security:password-encoder>

		</security:authentication-provider>

	</security:authentication-manager>

	<security:http use-expressions="true">
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<!-- admin role -->
		<security:intercept-url pattern="/admin" access="hasRole('ROLE_ADMIN')" />
		<!-- Method level security disabled -->
		<!-- <security:intercept-url pattern="/admin" access="permitAll" />-->
			
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<security:intercept-url pattern="/login" access="permitAll" />
		
		<security:intercept-url pattern="/loggedout" access="permitAll" />

		<security:intercept-url pattern="/denied" access="permitAll" />		
		
		
		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
		<security:logout logout-success-url="/loggedout" />
		<security:access-denied-handler error-page="/denied" />
		<security:remember-me key="noticesAppKey"
			user-service-ref="jdbcUserService" />
	</security:http>

	<security:global-method-security secured-annotations="enabled"></security:global-method-security>
<!--StandardPasswordEncoder class instance -->
	<bean id="passwordEncoder"
		class="org.springframework.security.crypto.password.StandardPasswordEncoder">
	</bean>

</beans>

```

### web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>springtutorial48</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  <servlet>
    <description></description>
    <display-name>notices</display-name>
    <servlet-name>notices</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>notices</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

     <description>Spring Test App</description>
  <resource-ref>
      <description>DB Connection</description>
      <res-ref-name>jdbc/spring</res-ref-name>
      <res-type>javax.sql.DataSource</res-type>
      <res-auth>Container</res-auth>
  </resource-ref> 
<!--listener added-->
  <listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener
	</listener-class>
  </listener>

  	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			classpath:com/spring/web/config/dao-context.xml
			classpath:com/spring/web/config/service-context.xml
			classpath:com/spring/web/config/security-context.xml

		</param-value>
	</context-param>

 <!-- Wiring TestFilter -->
 <!--
 <filter>
 <display-name>TestFilter</display-name>
 <filter-name>TestFilter</filter-name>
 <filter-class>com.spring.web.filter.TestFilter</filter-class>
 </filter> 
 
 <filter-mapping>
 <filter-name>TestFilter</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>
-->
  <!-- Spring security filter is added -->
  <filter>
    <display-name>springSecurityFilterChain</display-name>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
<!--Session timeout-->
  <session-config><session-timeout>1</session-timeout></session-config>

</web-app>
```


### default.xml
#### Location : WEB-INF/layout

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Tiles Definition File -->
<!DOCTYPE tiles-definitions PUBLIC "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN" "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">
<tiles-definitions>

	<definition name="notice.base" template="/WEB-INF/templates/default.jsp">
		<put-attribute name="title" value="Home"></put-attribute>
		<put-attribute name="header" value="/WEB-INF/tiles/header.jsp"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/content.jsp"></put-attribute>
		<put-attribute name="footer" value="/WEB-INF/tiles/footer.jsp"></put-attribute>			
	</definition>

	<definition name="home" extends="notice.base">
		<put-attribute name="title" value="Notice Home"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/home.jsp"></put-attribute>		
	</definition>

	<definition name="notices" extends="notice.base">
		<put-attribute name="title" value="Notice List"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/notices.jsp"></put-attribute>		
	</definition>

	<definition name="login" extends="notice.base">
		<put-attribute name="title" value="Notice Login"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/login.jsp"></put-attribute>		
	</definition>

	<definition name="newaccount" extends="notice.base">
		<put-attribute name="title" value="Create New Account"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/newaccount.jsp"></put-attribute>		
	</definition>

	<definition name="accountcreated" extends="notice.base">
		<put-attribute name="title" value="Account created "></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/accountcreated.jsp"></put-attribute>		
	</definition>	
	
	<definition name="createnotice" extends="notice.base">
		<put-attribute name="title" value="Create New Notice"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/createnotice.jsp"></put-attribute>		
	</definition>	

	<definition name="noticecreated" extends="notice.base">
		<put-attribute name="title" value="Notice Create Successfully"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/noticecreated.jsp"></put-attribute>		
	</definition>
	
	<definition name="admin" extends="notice.base">
		<put-attribute name="title" value="This is Admin Panel"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/admin.jsp"></put-attribute>		
	</definition>	
	
	<definition name="loggedout" extends="notice.base">
		<put-attribute name="title" value="Logged out Successfully"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/loggedout.jsp"></put-attribute>		
	</definition>

	<definition name="denied" extends="notice.base">
		<put-attribute name="title" value="Access Denied....."></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/denied.jsp"></put-attribute>		
	</definition>

	<definition name="error" extends="notice.base">
		<put-attribute name="title" value="Error....."></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/error.jsp"></put-attribute>		
	</definition>
	
</tiles-definitions>
```



# Lacture 121
## Objective : Using Logging in home controller

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# Location src/

# log4j.properties

```properties
	log4j.rootLogger=INFO, CONSOLE
	log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
	log4j.appender.CONSOLE.layout=org.apache.log4j.SimpleLayout
```

> Go to Run Configurations => Tomcat => Arguments => -Dlog4j.debug

# package name com.spring.web.controller


### HomeController.java

```java
package com.spring.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	private static Logger logger = Logger.getLogger(HomeController.class);
	@RequestMapping("/")
	public String showHome() {
		logger.info("Showing home.....");
		return "home";
	}
}
```

### NoticeController.java

```java
package com.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
	
	NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	//http://localhost:8081/notices/test?id=100
	//Getting URL Parameters
	@RequestMapping("/test")
	public String showTest(Model model , @RequestParam("id")String id) {
		System.out.println("ID is : "+id);
		return "home";
	}	
	
//	@ExceptionHandler(DataAccessException.class)
//	public String handleDatabaseException(DataAccessException ex) {
//		return "error";
//	}
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		
		//if throw exception it will be handled by Database error Handler
		//noticesService.throwTextException();
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	@RequestMapping("/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());
		return "createnotice";
	}
	//notice beans will inject automatically
	@RequestMapping(value = "/docreate", method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Notice notice, BindingResult result) {
		System.out.println(notice);
		
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "createnotice";
		}
		noticesService.create(notice);
//		else {
//			System.out.println("Form is validet");
//		}
		return "noticecreated";
	}
}
```


### ErrorHandler.java

```java
package com.spring.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedException ex) {
		return "denied";
	}
}
```

### LoginController.java

```java
package com.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web.dao.User;
import com.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	
	UsersService usersService;
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/denied")
	public String accessDenied() {
		return "denied";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		//user.setAuthority("user");

		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
	
		try {
			usersService.create(user);
		}catch(DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
			return "newaccount";
		}
		return "accountcreated";
	}	
}
```

# Package name com.spring.web.dao

### Notice.java

```java
package com.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//this package created by us for valid email annotation
import com.spring.web.validation.ValidEmail;

public class Notice {
	private int id;
	@Size(min=4  ,max=100, message="Name must be between 5 to 100")
	private String name;
	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
	// ValideEmail Annotation created by us, see import com.spring.web.validation.ValidEmail; package
	@ValidEmail
	private String email;
	@Size(min=20  ,max=250, message="text must be between 20 to 250")
	private String text;
	
	public Notice() {

	}	
	
	public Notice(String name, String email, String text) {
		this.name = name;
		this.email = email;
		this.text = text;
	}	
	
	public Notice(int id, String name, String email, String text) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
	}
	
}
```

### NoticesDAO.java

```java
package com.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
	public NoticesDAO() {//constructor added for debugg purposes
		System.out.println("Beans are configured");
	}	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<Notice> getNotices(){
		
		return jdbc.query("select * from notices", new RowMapper<Notice>() {
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setEmail(rs.getString("email"));
				notice.setText(rs.getString("text"));
				return notice;
			}
		});
	}
	
	public boolean delete(int id) { // delete method
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.update("delete from notices where id = :id", params ) == 1 ; // return true if success
	}
	
	@Transactional
	public int[] create(List<Notice> notices) {
		SqlParameterSource[] params =  SqlParameterSourceUtils.createBatch(notices.toArray());
		return jdbc.batchUpdate("insert into notices (id,name , email, text) values (:id,:name,:email,:text)", params);		
	}

	public boolean create(Notice notice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		return jdbc.update("insert into notices (name , email, text) values (:name,:email,:text)", params) == 1;
	}
	
	public boolean update(Notice notice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		return jdbc.update("update notices set name=:name,email=:email,text=:text where id=:id", params) == 1;
	}
	
	public Notice getNotice(int id){
		//কোন অর্ডার ফলো করতে হয় না।
		//MapSqlParameterSource params = new MapSqlParameterSource("name", "cm");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		//return jdbc.query("select * from notices where name = 'cm'", new RowMapper<Notice>() {
		return jdbc.queryForObject("select * from notices where id = :id", params, new RowMapper<Notice>() {
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setEmail(rs.getString("email"));
				notice.setText(rs.getString("text"));
				return notice;// return single object
			}
		});
	}
}
```


### User.java

```java
package com.spring.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.spring.web.validation.ValidEmail;

public class User {
	@NotBlank
	@Size(min=4  ,max=15)
	@Pattern(regexp="^\\w{4,}$")
	private String username;
	@NotBlank
	@Size(min=8  ,max=15)
	@Pattern(regexp="^\\S+$")	
	private String password;
	
	@ValidEmail
	private String email;
	private boolean enabled;
	private String authority;
	
	public User() {
		
	}

	public User(String username, String password, String email, boolean enabled, String authority) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
```

### UserDao.java

```java
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
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority()); 

		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
	}
	public List<User> getAllUsers() {
		return jdbc.query("select * from users , authorities where users.username = authorities.username", BeanPropertyRowMapper.newInstance(User.class));
	}
}
```


## package name com.spring.web.service

### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

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
	//create method for insert value 
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public void create(Notice notice) {
		noticesDAO.create(notice);
	}
}
```



### UsersService.java

```java
package com.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;
import java.util.List;


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
	public boolean exists(String username) {
		return userDao.exists(username);
	}
	//method level security enabled
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}	
}
```


### ValidEmail.java

#### under com.spring.web.validation package

```java
package com.spring.web.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {com.spring.web.validation.ValidEmailImpl.class })
public @interface ValidEmail {

	String message() default "Does not seem to be a valid email address";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	int min() default 5;

}

```
### ValidEmailImpl.java

```java
package com.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail,String>{
	int min ;
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		min = constraintAnnotation.min();	
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email.length() < min) {
			return false;
		}
		if (!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}
		return true;
	}
}
```

# package com.spring.web.message

## Message.properties

```properties
	Size.user.username = User Name must be between 4 to 15 long.....
	NotBlank.user.username = username cannot be blank
	Pattern.user.username = Username only consist number letter underscore

	NotBlank.user.password = Password cannot be blank.....
	Size.user.password = Password must be between 8 to 15 long
	Pattern.user.password = Password cannot contain any space

	ValidEmail.user.email= this does not seems valid email

	UnmatchedPasswords.user.password = Passwords must match.


	DuplicateKey.user.username = this username already exist, please choose different username
```


### home.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br>
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>

<!--</b>-->
</b>
```


### createnotice.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<div class="col-md-6 col-md-offset-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="notice">
		<fieldset>
			<!-- Form Name -->
			<legend>Create Notice</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="name">Name :</label>
				<div class="col-md-4">
					<!-- Path must be equal to input name -->
					<sf:input id="name" path="name" name="name" type="text"
						placeholder="Enter your name" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="name" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email :</label>
				<div class="col-md-4">
					<sf:input id="email" name="email" path="email" type="text"
						placeholder="Enter your email" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Notice :</label>
				<div class="col-md-4">
					<sf:textarea class="form-control" path="text" id="text" name="text"></sf:textarea>
					<!-- Show error message into view -->
					<sf:errors path="text" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Create
						Notice</button>
				</div>
			</div>
		</fieldset>
	</sf:form>
</div>
```

### notices.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
 -->
<table class="table table-striped">
	<tr>
		<th scope="col">ID</th>
		<th scope="col">Name</th>
		<th scope="col">Email</th>
		<th scope="col">Notice</th>
	</tr>
	<c:forEach var="notice" items="${notices}">
		<tr>
			<td><c:out value="${notice.id}"></c:out></td>
			<td><c:out value="${notice.name}"></c:out></td>
			<td><c:out value="${notice.email}"></c:out></td>
			<td><c:out value="${notice.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>
```

### noticecreated.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a href="${pageContext.request.contextPath}/notices">Current notices</a><br>

<!--</b>-->
</b>
```

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(j_username).focus();
	});
</script>
<h3>Login with Username and Password</h3>
<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal"
		action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<fieldset>
			<!-- Form Name -->
			<legend>Login</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_username">Username
					:</label>
				<div class="col-md-4">
					<input id="j_username" name="j_username" type="text" placeholder=""
						class="form-control input-md">
				</div>
			</div>
			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_password">Password
					:</label>
				<div class="col-md-4">
					<input id="j_password" name="j_password" type="password"
						placeholder="" class="form-control input-md">
				</div>
			</div>
			<div class="alert-danger">
				<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
			</div>
			<!-- Checkbox input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_password">Remember
					me :</label> <input id="_spring_security_remember_me"
					name="_spring_security_remember_me" type="checkbox"
					checked="checked">
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</fieldset>
	</form>
	<p>
		<a href="<c:url value='/newaccount'/>">create new Account</a>
	</p>
</div>
```



### newaccount.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<div class="col-md-6 col-md-offset-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<fieldset>
			<!-- Form Name -->
			<legend>Create User</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username">Username
					: </label>
				<div class="col-md-4">
					<!-- Path must be equal to input name -->
					<sf:input id="username" path="username" name="username" type="text"
						placeholder="Enter your username" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- email input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email :</label>
				<div class="col-md-4">
					<sf:input id="email" name="email" path="email" type="text"
						placeholder="Enter your email" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Password
					:</label>
				<div class="col-md-4">
					<sf:input id="password" name="password" path="password"
						type="password" class="form-control input-md" />
					<sf:errors path="password" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!--Conform Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="confirmpassword">Confirm
					Password :</label>
				<div class="col-md-4">
					<input id="confirmpassword" name="confirmpassword" type="password"
						class="form-control input-md" />
				</div>
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Create
						User</button>
				</div>
			</div>
		</fieldset>
	</sf:form>
</div>
<script>
	var password1 = document.getElementById('password');
	var password2 = document.getElementById('confirmpassword');
	var checkPasswordValidity = function() {
		if (password1.value != password2.value) {
			password1
					.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
		} else {
			password1.setCustomValidity('');
		}
	};
	password1.addEventListener('change', checkPasswordValidity, false);
	password2.addEventListener('change', checkPasswordValidity, false);
	/***commandName="user"***/
	var form = document.getElementById('user');
	form.addEventListener('submit', function() {
		checkPasswordValidity();
		if (!this.checkValidity()) {
			event.preventDefault();
			//Implement you own means of displaying error messages to the user here.
			password1.focus();
		}
	}, false);
</script>
```

### accountcreated.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Your account has been created.......

<!--</b>-->
</b>
```

### loggedout.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
You have logged out........

<!--</b>-->
</b>
```


### admin.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-striped">
	<tr>
		<th scope="col">Username</th>
		<th scope="col">Email</th>
		<th scope="col">Authority</th>
		<th scope="col">Enabled</th>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr>
			<td><c:out value="${user.username}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
			<td><c:out value="${user.authority}"></c:out></td>
			<td><c:out value="${user.enabled}"></c:out></td>
		</tr>
	</c:forEach>
</table>
```

### denied.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Access denied....

</br>
```

### error.jsp
#### Location : WEB-INF/templates
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
a database error occured, please check your connection or try again later

</br>
```


### default.jsp
#### Location : WEB-INF/templates

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- apache tiles taglib -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><tiles:getAsString name="title"></tiles:getAsString></title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/static/lib/bootstrap/css/bootstrap.css'/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value='/static/css/notice-main.css'/>" rel="stylesheet">
	<script src="<c:url value='/static/js/jquery-3.3.1.slim.min.js'/>"></script>
	<script src="<c:url value='/static/lib/bootstrap/js/bootstrap.min.js'/>"></script>
  </head>
  <body>
		<!-- 	<h1>hello from tiles</h1> -->
	<div>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<main role="main" class="container">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
    </main><!-- /.container -->
	<div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>
```

### header.jsp
#### Location : WEB-INF/tiles

```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<a class="navbar-brand" href="#">Notices</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<!-- Header file in -->
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="<c:url value='/'/>">Home
			</a></li>
		</ul>
		<ul class = "nav navbar-nav navbar-right">	
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/login'/>">Log In</a> </li>
			</sec:authorize>					
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"> <a  class="nav-link"  href="<c:url value='/j_spring_security_logout'/>">Log Out</a> </li>
			</sec:authorize>
		</ul>
	</div>
</nav>

<!--</br>-->
</br>
```

### content.jsp
#### Location : WEB-INF/tiles

```java
<div class="starter-template">
	<h1>Bootstrap starter template</h1>
	<p class="lead">
		Use this document as a way to quickly start any new project.<br>
		All you get is this text and a mostly barebones HTML document.
	</p>
</div>
```

### footer.jsp
#### Location : WEB-INF/tiles

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```

### service-context.xml

#### Under com.spring.web.config Package

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.service"></context:component-scan>
</beans>

```

### dao-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jee="http://www.springframework.org/schema/jee"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">

	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>


	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!--tx active-->
	<tx:annotation-driven />
</beans>
```


### notices-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:component-scan base-package="com.spring.controller"></context:component-scan>
	<mvc:annotation-driven></mvc:annotation-driven>

	<!--Enable bootstrap-->
		<mvc:resources location="/resources/" mapping="/static/**" />

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
	</property>
	</bean>

	<!--Tiles view Resolver-->
	<bean id="tilesViewResolver"
		class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
	</bean>
	
	<bean id="tilesConfigurer"
		class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
	<property name="definitions">
		<list>
			<value>/WEB-INF/layout/default.xml</value>
		</list>
	</property>
	</bean>





</beans>

```

### security-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-3.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


	<security:authentication-manager>

		
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource"
				authorities-by-username-query="SELECT * FROM authorities where binary username = ?"
				users-by-username-query="SELECT * FROM users where binary username = ?"
				id="jdbcUserService" />


			<!-- taking reference from passwordEncoder -->
			<security:password-encoder ref="passwordEncoder"></security:password-encoder>

		</security:authentication-provider>

	</security:authentication-manager>

	<security:http use-expressions="true">
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<!-- admin role -->
		<security:intercept-url pattern="/admin" access="hasRole('ROLE_ADMIN')" />
		<!-- Method level security disabled -->
		<!-- <security:intercept-url pattern="/admin" access="permitAll" />-->
			
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<security:intercept-url pattern="/login" access="permitAll" />
		
		<security:intercept-url pattern="/loggedout" access="permitAll" />

		<security:intercept-url pattern="/denied" access="permitAll" />		
		
		
		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
		<security:logout logout-success-url="/loggedout" />
		<security:access-denied-handler error-page="/denied" />
		<security:remember-me key="noticesAppKey"
			user-service-ref="jdbcUserService" />
	</security:http>

	<security:global-method-security secured-annotations="enabled"></security:global-method-security>
<!--StandardPasswordEncoder class instance -->
	<bean id="passwordEncoder"
		class="org.springframework.security.crypto.password.StandardPasswordEncoder">
	</bean>

</beans>

```

### web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>springtutorial48</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  <servlet>
    <description></description>
    <display-name>notices</display-name>
    <servlet-name>notices</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>notices</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

     <description>Spring Test App</description>
  <resource-ref>
      <description>DB Connection</description>
      <res-ref-name>jdbc/spring</res-ref-name>
      <res-type>javax.sql.DataSource</res-type>
      <res-auth>Container</res-auth>
  </resource-ref> 
<!--listener added-->
  <listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener
	</listener-class>
  </listener>

  	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			classpath:com/spring/web/config/dao-context.xml
			classpath:com/spring/web/config/service-context.xml
			classpath:com/spring/web/config/security-context.xml

		</param-value>
	</context-param>

 <!-- Wiring TestFilter -->
 <!--
 <filter>
 <display-name>TestFilter</display-name>
 <filter-name>TestFilter</filter-name>
 <filter-class>com.spring.web.filter.TestFilter</filter-class>
 </filter> 
 
 <filter-mapping>
 <filter-name>TestFilter</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>
-->
  <!-- Spring security filter is added -->
  <filter>
    <display-name>springSecurityFilterChain</display-name>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
<!--Session timeout-->
  <session-config><session-timeout>1</session-timeout></session-config>

</web-app>
```


### default.xml
#### Location : WEB-INF/layout

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Tiles Definition File -->
<!DOCTYPE tiles-definitions PUBLIC "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN" "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">
<tiles-definitions>

	<definition name="notice.base" template="/WEB-INF/templates/default.jsp">
		<put-attribute name="title" value="Home"></put-attribute>
		<put-attribute name="header" value="/WEB-INF/tiles/header.jsp"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/content.jsp"></put-attribute>
		<put-attribute name="footer" value="/WEB-INF/tiles/footer.jsp"></put-attribute>			
	</definition>

	<definition name="home" extends="notice.base">
		<put-attribute name="title" value="Notice Home"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/home.jsp"></put-attribute>		
	</definition>

	<definition name="notices" extends="notice.base">
		<put-attribute name="title" value="Notice List"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/notices.jsp"></put-attribute>		
	</definition>

	<definition name="login" extends="notice.base">
		<put-attribute name="title" value="Notice Login"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/login.jsp"></put-attribute>		
	</definition>

	<definition name="newaccount" extends="notice.base">
		<put-attribute name="title" value="Create New Account"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/newaccount.jsp"></put-attribute>		
	</definition>

	<definition name="accountcreated" extends="notice.base">
		<put-attribute name="title" value="Account created "></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/accountcreated.jsp"></put-attribute>		
	</definition>	
	
	<definition name="createnotice" extends="notice.base">
		<put-attribute name="title" value="Create New Notice"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/createnotice.jsp"></put-attribute>		
	</definition>	

	<definition name="noticecreated" extends="notice.base">
		<put-attribute name="title" value="Notice Create Successfully"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/noticecreated.jsp"></put-attribute>		
	</definition>
	
	<definition name="admin" extends="notice.base">
		<put-attribute name="title" value="This is Admin Panel"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/admin.jsp"></put-attribute>		
	</definition>	
	
	<definition name="loggedout" extends="notice.base">
		<put-attribute name="title" value="Logged out Successfully"></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/loggedout.jsp"></put-attribute>		
	</definition>

	<definition name="denied" extends="notice.base">
		<put-attribute name="title" value="Access Denied....."></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/denied.jsp"></put-attribute>		
	</definition>

	<definition name="error" extends="notice.base">
		<put-attribute name="title" value="Error....."></put-attribute>
		<put-attribute name="content" value="/WEB-INF/tiles/error.jsp"></put-attribute>		
	</definition>
	
</tiles-definitions>
```