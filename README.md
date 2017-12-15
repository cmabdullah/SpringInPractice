

# Documentation of Spring Framework

> java -version


### STS 3.6.2.RELEASE

> tar zxvf /Users/abdullah

> sudo apt-get install maven

> mvn

### Download Maven documents form IDE

# Tamplete

* org.apache.maven.arctypes maven-arctype-quickstart

	Group Id com.abdullah.spring.test
	
	Artificial ID testspring

## pom.xml

* maven project object model
* we can define all project dependency
* dependency can be jar file, or arc type project

## Dependency
* spring-core 3.2.11
* spring-beans 3.2.11
* spring-context 3.2.11

# Lacture 4
## Objective : Define patient class beans
### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class App 
{
    public static void main( String[] args )
    {
		//beans.xml is available on applications root path
    	ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");//load beans.xml through Spring ApplicationContext
		Patient patient = (Patient)context.getBean("patient");// return object thats why cust Patient type
   		 patient.speak();
    ((FileSystemXmlApplicationContext)context).close();// close FileSystemXmlApplicationContext
    }
}
```

### Patient.java
```java
package com.cma.spring.exceptiontest;
import java.util.List;
public class Patient {
	public void speak(){
		System.out.println("Help me");
	}
}
```

### beans.xml
* New > Spring Bean Configuration File
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	default-init-method="init" default-destroy-method="destroy"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient">
	</bean>
</beans>
```


# Lacture 5
## Objective : Load beans.xml through Class Path Xml Application Context
### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App 
{
    public static void main( String[] args )
    {
		//beans.xml is available on applications root path
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");//load beans.xml through Spring ApplicationContext
		Patient patient = (Patient)context.getBean("patient");// return object thats why cust Patient type
   		 patient.speak();
    ((ClassPathXmlApplicationContext)context).close();// close ClassPathXmlApplicationContext
    }
}
```

### Patient.java
```java
package com.cma.spring.exceptiontest;
import java.util.List;
public class Patient {
	public void speak(){
		System.out.println("Help me");
	}
}
```

### beans.xml
* New > Spring Bean Configuration File
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	default-init-method="init" default-destroy-method="destroy"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient">
	</bean>
</beans>
```