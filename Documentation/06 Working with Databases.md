# Lacture 37/1
## Objective : Using Property Files (XML)
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Parrot parrot = (Parrot) context.getBean("parrot");
    	parrot.speek();
    ((ClassPathXmlApplicationContext)context).close();
    }
}

```



### Parrot.java

```java
package com.cma.spring.exceptiontest;

public class Parrot {
	private String id ;
	private String speech ;

	public void setId(String id) {
		this.id = id;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}
}


```

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.user = root
	jdbc.password = rootcm
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<bean id="parrot" class="com.cma.spring.exceptiontest.Parrot">
	<property name="id" value='${jdbc.user}'></property>
	<property name="speech" value="${jdbc.password}"></property>
	</bean>
	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
</beans>
```


# Lacture 37/2
## Objective : Using Property Files (Annotation)
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Parrot parrot = (Parrot) context.getBean("parrot");
    	parrot.speek();
    ((ClassPathXmlApplicationContext)context).close();
    }
}

```



### Parrot.java

```java
package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parrot {
	private String id = "123";
	private String speech ="Hi cm" ;

	@Autowired
	public void setId(@Value("${jdbc.user}")String id) {
		this.id = id;
	}
	@Autowired
	public void setSpeech(@Value("${jdbc.password}")String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}
}
```

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.user = root
	jdbc.password = rootcm
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
</beans>
```



# Lacture 38-41
## Objective : Jdbc Template and Querying the Database with exception
### App.java

```java
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
    		List<Notice> notices = noticesDAO.getNotice();
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
```



### Notice.java

```java
package com.cma.spring.exceptiontest;

public class Notice {
	private int id;
	private String name;
	private String email;
	private String text;
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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private JdbcTemplate jdbc;
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new JdbcTemplate(jdbc);
	}
	public List<Notice> getNotice(){
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
}
```

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```


# Lacture 42/1
## Objective : Spring Framework - Using Named Parameters, (Single parameter)
### App.java

```java
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
    		List<Notice> notices = noticesDAO.getNotice();
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
```



### Notice.java

```java
package com.cma.spring.exceptiontest;

public class Notice {
	private int id;
	private String name;
	private String email;
	private String text;
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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<Notice> getNotice(){
				//কোন অর্ডার ফলো করতে হয় না।
		//MapSqlParameterSource params = new MapSqlParameterSource("name", "cm");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "cm");
		//return jdbc.query("select * from notices where name = 'cm'", new RowMapper<Notice>() {
		return jdbc.query("select * from notices where name = :name", params, new RowMapper<Notice>() {
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
}

```

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```




# Lacture 42/2
## Objective : Spring Framework - Using Named Parameters, (return single object)
### App.java

```java
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

```



### Notice.java

```java
package com.cma.spring.exceptiontest;

public class Notice {
	private int id;
	private String name;
	private String email;
	private String text;
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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
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

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```

# Lacture 43
## Objective : Update method, delete query
### App.java

```java
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
    		
    		
    		noticesDAO.delete(2); //delete method called
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
```



### Notice.java

```java
package com.cma.spring.exceptiontest;

public class Notice {
	private int id;
	private String name;
	private String email;
	private String text;
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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
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

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```




# Lacture 44
## Objective : Getting Placeholder Values from Beans(Insert into databases)
### App.java

```java
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
    		//insert notice
    		
    		Notice notice1 = new Notice("Abdullah", "cm@gmail.com", "hi Cm How are you?");
    		noticesDAO.create(notice1);
    		
    		
    		noticesDAO.delete(2); //delete method called
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
```



### Notice.java

```java
package com.cma.spring.exceptiontest;

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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
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

	public boolean create(Notice notice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);
		return jdbc.update("insert into notices (name , email, text) values (:name,:email,:text)", params) == 1;
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

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```

# Lacture 45
## Objective : Adding Update method
### App.java

```java
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
    		//insert notice
    		
    		//Notice notice1 = new Notice("Abdullah", "cm@gmail.com", "hi Cm How are you?");
    		//noticesDAO.create(notice1);

    		Notice notice1 = new Notice(2,"Abi Abdullah Khan", "abiabdullah@gmail.com", "welcome abi abdullah"); //seems fixed bug from Id
    		noticesDAO.update(notice1); //update notice
    		
    		
    		noticesDAO.delete(2); //delete method called
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
```



### Notice.java

```java
package com.cma.spring.exceptiontest;

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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
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

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```



# Lacture 46
## Objective : Batch update
### App.java

```java
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
    		
    		Notice notice1 = new Notice("mainul1", "mainul@gmail.com", "hi mainul How are you?");
    		Notice notice2 = new Notice("mainul2", "mainul@gmail.com", "hi mainul How are you?");
    		Notice notice3 = new Notice("mainul3", "mainul@gmail.com", "hi mainul How are you?");
    		Notice notice4 = new Notice("mainul4", "mainul@gmail.com", "hi mainul How are you?");
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
```



### Notice.java

```java
package com.cma.spring.exceptiontest;

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
package com.cma.spring.exceptiontest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("noticesDAO")
public class NoticesDAO {
	private NamedParameterJdbcTemplate jdbc;
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

	//Batch Update
	public int[] create(List<Notice> notices) {
		SqlParameterSource[] params =  SqlParameterSourceUtils.createBatch(notices.toArray());
		return jdbc.batchUpdate("insert into notices (name , email, text) values (:name,:email,:text)", params);		
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

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```


# Lacture 47
## Objective : @Transactional
### App.java

	<tx:annotation-driven /><!--@Transactional-->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>


```java
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
    		//pass index also
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

```



### Notice.java

```java
package com.cma.spring.exceptiontest;

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
package com.cma.spring.exceptiontest;

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

### jdbc.properties

> location="com/cma/spring/exceptiontest/props/jdbc.properties"

```properties
	jdbc.username = root
	jdbc.password = rootcm
	jdbc.driver = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost:3306/springtutorial?autoReconnect=true&useSSL=false
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">

	<context:property-placeholder
		location="com/cma/spring/exceptiontest/props/jdbc.properties" />
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
	</bean>
	<tx:annotation-driven /><!--@Transactional-->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
	</bean>
</beans>
```
