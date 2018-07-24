# Lacture 63
## Objective : Creating a Form


1. context.xml (Tomcat server)

	<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
	maxActive="100" maxIdle="30" maxWait="10000"
	username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/springtutorial"/>


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}

	@RequestMapping("/createnotice")
	public String createNotice() {
		return "createnotice";
	}
	@RequestMapping("/docreate")
	public String doCreate() {
		return "noticecreated";
	}
}
```

### Notice.java

```java
package com.spring.web.dao;

public class Notice {
	private int id;
	private String name;
	private String email;
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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
```



### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/docreate">
<table>
<tr> <td>Name :</td><td><input name = "name" type = "text"/></td>   </tr>
<tr> <td>Email :</td><td><input name = "email" type = "text"/></td> 
<tr> <td>Notice :</td><td><textarea rows="10" cols="15" name = "text"></textarea></td> 
<tr> <td>&nbsp;</td><td><input name = "create query" type = "submit"/></td> 
</table>


</form>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
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

		</param-value>
	</context-param>
</web-app>
```



# Lacture 64
## Objective : Getting Form Values


1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial"/>


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}

	@RequestMapping("/createnotice")
	public String createNotice() {
		return "createnotice";
	}

	//notice beans will inject automatically
	@RequestMapping(value = "/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, Notice notice) {
		System.out.println(notice);
		return "noticecreated";
	}
}
```

### Notice.java

```java
package com.spring.web.dao;

public class Notice {
	private int id;
	private String name;
	private String email;
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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
```



### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<form method="post" action="${pageContext.request.contextPath}/docreate">
<table>
<tr> <td>Name :</td><td><input name = "name" type = "text"/></td>   </tr>
<tr> <td>Email :</td><td><input name = "email" type = "text"/></td> 
<tr> <td>Notice :</td><td><textarea rows="10" cols="15" name = "text"></textarea></td> 
<tr> <td>&nbsp;</td><td><input name = "create query" type = "submit"/></td> 
</table>


</form>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
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

		</param-value>
	</context-param>
</web-app>
```


# Lacture 65
## Objective : Bringing in Bootstrap


1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial"/>


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}

	@RequestMapping("/createnotice")
	public String createNotice() {
		return "createnotice";
	}

	//notice beans will inject automatically
	@RequestMapping(value = "/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, Notice notice) {
		System.out.println(notice);
		return "noticecreated";
	}
}
```

### Notice.java

```java
package com.spring.web.dao;

public class Notice {
	private int id;
	private String name;
	private String email;
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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
```



### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load bootstrap library-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<form method="post" action="${pageContext.request.contextPath}/docreate">
<table>
<tr> <td>Name :</td><td><input name = "name" type = "text"/></td>   </tr>
<tr> <td>Email :</td><td><input name = "email" type = "text"/></td> 
<tr> <td>Notice :</td><td><textarea rows="10" cols="15" name = "text"></textarea></td> 
<tr> <td>&nbsp;</td><td><input name = "create query" type = "submit"/></td> 
</table>


</form>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```



# Lacture 66-67
## Objective : Adding Hibernate Form Validation Support


1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial"/>




	  	<dependency>
	  		<groupId>org.hibernate</groupId>
	  		<artifactId>hibernate</artifactId>
	  		<version>3.5.4-Final</version>
	  		<type>pom</type>
	  	</dependency>
	  	<dependency>
	  		<groupId>javax.validation</groupId>
	  		<artifactId>validation-api</artifactId>
	  		<version>1.1.0.Final</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.hibernate</groupId>
	  		<artifactId>hibernate-validator</artifactId>
	  		<version>5.1.3.Final</version>
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		
	
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	
	
	

	@RequestMapping("/createnotice")
	public String createNotice() {
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
		}else {
			System.out.println("Form is validet");
		}
		return "noticecreated";
	}
}

```

### Notice.java

```java
package com.spring.web.dao;

import javax.validation.constraints.Size;

public class Notice {
	private int id;
	@Size(min=4  ,max=100)
	private String name;
	private String email;
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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
```



### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load bootstrap library-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>

<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->

 <div class="col-md-6 col-md-offset-3">
<form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <input id="name" name="name" type="text" placeholder="Enter your name" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <input id="email" name="email" type="text" placeholder="Enter your email" class="form-control input-md">
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="text" name="text"></textarea>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</form>
</div>





</div>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```






# Lacture 68
## Objective : More Form Validation Support


1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial"/>




	  	<dependency>
	  		<groupId>org.hibernate</groupId>
	  		<artifactId>hibernate</artifactId>
	  		<version>3.5.4-Final</version>
	  		<type>pom</type>
	  	</dependency>
	  	<dependency>
	  		<groupId>javax.validation</groupId>
	  		<artifactId>validation-api</artifactId>
	  		<version>1.1.0.Final</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.hibernate</groupId>
	  		<artifactId>hibernate-validator</artifactId>
	  		<version>5.1.3.Final</version>
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		
	
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	
	
	

	@RequestMapping("/createnotice")
	public String createNotice() {
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
		}else {
			System.out.println("Form is validet");
		}
		return "noticecreated";
	}
}

```

### Notice.java

```java
package com.spring.web.dao;

import javax.validation.constraints.Size;

public class Notice {
	private int id;
	@Size(min=4  ,max=100, message="Name must be between 5 to 100")
	private String name;
	@NotNull
	@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
```



### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load bootstrap library-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>

<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->

 <div class="col-md-6 col-md-offset-3">
<form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <input id="name" name="name" type="text" placeholder="Enter your name" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <input id="email" name="email" type="text" placeholder="Enter your email" class="form-control input-md">
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="text" name="text"></textarea>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</form>
</div>





</div>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```


# Lacture 69-70
## Objective : Retaining Form Information & Displaying Form Validation Errors


1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial"/>


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		
	
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	
	
	

	@RequestMapping("/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());//add attribute into model
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
		}else {
			System.out.println("Form is validet");
		}
		return "noticecreated";
	}
}

```

### Notice.java

```java
package com.spring.web.dao;

import javax.validation.constraints.Size;

public class Notice {
	private int id;
	@Size(min=4  ,max=100, message="Name must be between 5 to 100")
	private String name;
	@NotNull
	@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
```



### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->

 <div class="col-md-6 col-md-offset-3">
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate" commandName="notice">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="name" path="name" name="name"  type="text" placeholder="Enter your name" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="name" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <sf:textarea class="form-control" path="text" id="text" name="text" ></sf:textarea>
     <!-- Show error message into view -->
    <sf:errors path="text" cssClass="alert-danger"></sf:errors>   
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</sf:form>
</div>





</div>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```

# Lacture 71
## Objective : Creating a Custom Validation Annotation 

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial"/>


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		
	
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	
	
	

	@RequestMapping("/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());//add attribute into model
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
		}else {
			System.out.println("Form is validet");
		}
		return "noticecreated";
	}
}

```

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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->

 <div class="col-md-6 col-md-offset-3">
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate" commandName="notice">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="name" path="name" name="name"  type="text" placeholder="Enter your name" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="name" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <sf:textarea class="form-control" path="text" id="text" name="text" ></sf:textarea>
     <!-- Show error message into view -->
    <sf:errors path="text" cssClass="alert-danger"></sf:errors>   
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</sf:form>
</div>

</div>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```
### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```





# Lacture 72
## Objective : Adding Database Codes, insert value from view to database and show jsp view tablewise

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		
	
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	
	
	

	@RequestMapping("/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());//add attribute into model
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
		//else {
		//	System.out.println("Form is validet");
		//}
		return "noticecreated";
	}
}

```

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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	//create method for insert value 
	public void create(Notice notice) {
		noticesDAO.create(notice);
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


### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->

 <div class="col-md-6 col-md-offset-3">
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate" commandName="notice">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="name" path="name" name="name"  type="text" placeholder="Enter your name" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="name" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <sf:textarea class="form-control" path="text" id="text" name="text" ></sf:textarea>
     <!-- Show error message into view -->
    <sf:errors path="text" cssClass="alert-danger"></sf:errors>   
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</sf:form>
</div>

</div>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--bootstrup added-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!--Show value tablewise-->
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
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```

### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```



# Lacture 73
## Objective : Exception handling in Spring MVC

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


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


### DatabaseErrorHandler.java

```java
package com.spring.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class DatabaseErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	}
}

```




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


### NoticesService.java

```java
package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	//create method for insert value 
	public void create(Notice notice) {
		noticesDAO.create(notice);
	}	

	// error handle test method
	public void throwTextException() {
		noticesDAO.getNotice(132332);
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


### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
	</body>
</html>
```


### createnotice.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->

 <div class="col-md-6 col-md-offset-3">
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate" commandName="notice">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="name" path="name" name="name"  type="text" placeholder="Enter your name" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="name" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <sf:textarea class="form-control" path="text" id="text" name="text" ></sf:textarea>
     <!-- Show error message into view -->
    <sf:errors path="text" cssClass="alert-danger"></sf:errors>   
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</sf:form>
</div>

</div>
</body>
</html>
```

### notices.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--bootstrup added-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!--Show value tablewise-->
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
</body>
</html>
```

### noticecreated.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice created</h1>
</body>
</html>
```

### service-context.xml

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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.web.dao"></context:component-scan>
	<!--dataSource bean is created-->
	<jee:jndi-lookup jndi-name="jdbc/spring" id="dataSource"
		expected-type="javax.sql.DataSource">
	</jee:jndi-lookup>
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

	<!--Internal Resource View Resolver-->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		id="jspViewResolver">
	<property name="prefix" value="/WEB-INF/jsps/"></property>
	<property name="suffix" value=".jsp"></property>
	</bean>	

	<!--Enable bootstrap-->


		<mvc:resources location="/resources/" mapping="/static/**" />
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

		</param-value>
	</context-param>
</web-app>
```