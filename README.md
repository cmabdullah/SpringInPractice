

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

### some basic commands
1. command+shift+r = open resource
2. command+space = full line
3. command+shift+f = rearrange

# Lacture 4
## Objective : Define patient class beans
### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class App {
    public static void main( String[] args ){
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
public class App {
    public static void main( String[] args ) {
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


# Lacture 6
## Objective : Beans Constructor Arguments
###### If a class has some properties, wants to initialize through beans Constructor, beans access attributs through getters and setters, coz its a private attribute 

### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");//load beans.xml through Spring ApplicationContext
		Patient patient = (Patient)context.getBean("patient");// return object thats why cust Patient type
		System.out.println( patient );
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
	private int id;
	private String name;
	public Patient() {//empty constructor	
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public int getId() {//beans access attributs through getters and setters
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
	@Override
	public String toString() {//used for print object
		return "Patient [id=" + id + ", name=" + name + "]";
	}
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
	<constructor-arg value="5" name="id"></constructor-arg><!-- Add constructor arguments -->
	<constructor-arg value="cm" name="name"></constructor-arg>
	</bean>
</beans>
```

# Lacture 7
## Objective : How Define Beans Property

### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");//load beans.xml through Spring ApplicationContext
		Patient patient = (Patient)context.getBean("patient");// return object thats why cust Patient type
		System.out.println( patient );
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
	private int id;
	private String name;
	private int nationalId;//initialize an attribute without constructor, through parameter @cm
	public Patient() {//empty constructor	
	}
	public int getNationalId() {
		return nationalId;
	}
	public void setNationalId(int nationalId) {
		this.nationalId = nationalId;
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public int getId() {//beans access attributs through getters and setters
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
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", nationalId="+ nationalId + "]";
	}
	public void speak(){
		System.out.println("Help me");
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	default-init-method="init" default-destroy-method="destroy"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient">
	<constructor-arg value="5" name="id"></constructor-arg><!-- Add constructor arguments -->
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property><!--property added that execute without constructor-->
	<!-- same code, just different image
	<property name="nationalId">
	 <value>1234</value>
	</property>
	-->
	</bean>
</beans>
```

# Lacture 8
## Objective : How perform dependency injection through Spring
## What is dependency injection
#### if any object1 is dependent on another object2, that time object2 make an instance through Spring and initialize on object1
##### How we inject Address class into Patient class?
##### How we inject Address object into patient class?

### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");//load beans.xml through Spring ApplicationContext
		Patient patient = (Patient)context.getBean("patient");// return object thats why cust Patient type
		System.out.println( patient ); 
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
	private int id;
	private String name;
	private int nationalId;//initialize an attribute without constructor, through parameter @cm
		private Address address; // it is a dependency of Patient class
	public Patient() {//empty constructor	
	}
	public int getNationalId() {
		return nationalId;
	}
	public void setNationalId(int nationalId) {
		this.nationalId = nationalId;
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {//beans access attributs through getters and setters
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
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", nationalId="+ nationalId + ", address=" + address + "]";

	}
	public void speak(){
		System.out.println("Help me");
	}
}
```



### Address.java
```java
package com.cma.spring.exceptiontest;
public class Address {
	private String street;
	private String postcode;
	public Address(){
	}
	
	public Address(String street, String postcode) {
		this.street = street;
		this.postcode = postcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", postcode=" + postcode + "]";
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	default-init-method="init" default-destroy-method="destroy"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient">
	<constructor-arg value="5" name="id"></constructor-arg><!-- Add constructor arguments -->
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property>
	<!--new Property for dependency injection-->
	<property name="address" ref="address"></property><!--property name of Patient class is address, and reference is "bean id="address" ..." -->

	</bean>
	<!--we have to initialize some variable using constructor-->
	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address>
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>

</beans>
```

# Lacture 9
## Objective : Bean Scope, difference between prototype and singleton scope ?
##### by default each beans works as a singleton
##### when we call a beans through Spring from a App , that time she give same object referrence many time, thats why called singleton.





### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");//load beans.xml through Spring ApplicationContext
		Patient patient1 = (Patient)context.getBean("patient");
		Patient patient2 = (Patient)context.getBean("patient");
		patient1.setName("Rafid");
		System.out.println( patient2 );
    ((ClassPathXmlApplicationContext)context).close();// close ClassPathXmlApplicationContext
    }
}
```

### Patient.java
```java
package com.cma.spring.exceptiontest;
import java.util.List;
public class Patient {
	private int id;
	private String name;
	private int nationalId;//initialize an attribute without constructor, through parameter @cm
		private Address address; // it is a dependency of Patient class
	public Patient() {//empty constructor	
	}
	public int getNationalId() {
		return nationalId;
	}
	public void setNationalId(int nationalId) {
		this.nationalId = nationalId;
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {//beans access attributs through getters and setters
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
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", nationalId="+ nationalId + ", address=" + address + "]";
	}
}
```



### Address.java
```java
package com.cma.spring.exceptiontest;
public class Address {
	private String street;
	private String postcode;
	public Address(){
	}
	public Address(String street, String postcode) {
		this.street = street;
		this.postcode = postcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", postcode=" + postcode + "]";
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	default-init-method="init" default-destroy-method="destroy"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient"
	scope="prototype"><!--here scope is ont singleton its prototype , always return different object-->
	<constructor-arg value="5" name="id"></constructor-arg><!-- Add constructor arguments -->
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property>
	<!--new Property for dependency injection-->
	<property name="address" ref="address"></property><!--property name of Patient class is address, and reference is "bean id="address" ..." -->
	</bean>
	<!--we have to initialize some variable using constructor-->
	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address>
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>

</beans>
```