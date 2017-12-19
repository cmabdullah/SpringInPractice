

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
	class="com.cma.spring.exceptiontest.Address">
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
	class="com.cma.spring.exceptiontest.Address">
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>

</beans>
```


# Lacture 10
## Objective : Bean Init and Destroy methods

##### Spring call destroy method only for singleton object
##### when a bean create, after initialize value , then bean will destroyed. that time we may need to run another method


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

	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
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
	public void init(){// call by beans from global decleration
		System.out.println("Address Created :"+this);
	}
	public void destroy(){
		System.out.println("Address Destroyed");
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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	default-init-method="init" default-destroy-method="destroy"><!--define globally-->


	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient"
	scope="singleton" init-method="onCreate" destroy-method="onDesteoy"><!--initialize mathod, then destroy mathod-->

	<constructor-arg value="5" name="id"></constructor-arg>
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property>
	<!--new Property for dependency injection-->
	<property name="address" ref="address"></property>
	</bean>
	<!--we have to initialize some variable using constructor-->
	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address">
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>
	
</beans>
```






# Lacture 11 part 1
## Objective : Factory Method
##### Due to some constructor issue, or sum dependency issue, we want to run program without constructor method, that itme we can use factory method.
##### we have to create a factory method, through factory method to beans configuration, we have to take patient beans

### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		System.out.println( patient );
    ((ClassPathXmlApplicationContext)context).close();
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
	private int nationalId;
		private Address address;
	public Patient() {	
	}
	// getInstance method returns a patient object instance
	public static Patient getInstance(int id, String name){// pass constructor arguments through beans
		System.out.println("Creating patient using factory method :");	
		return new Patient(id,name);
	}
	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
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
	public void init(){
		System.out.println("Address Created :"+this);
	}
	public void destroy(){
		System.out.println("Address Destroyed");
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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	default-init-method="init" default-destroy-method="destroy"><!--define globally-->


	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient"
	scope="singleton" init-method="onCreate" destroy-method="onDesteoy"
	
	factory-method="getInstance"> <!--call getInstance through factory method-->

	<constructor-arg value="5" name="id"></constructor-arg>
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property>
	<!--new Property for dependency injection-->
	<property name="address" ref="address"></property>
	</bean>
	<!--we have to initialize some variable using constructor-->
	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address">
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>
	
</beans>
```




# Lacture 11 part 2
## Objective : Factory Class

### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		System.out.println( patient );
    ((ClassPathXmlApplicationContext)context).close();
    }
}
```


### PatientFactory.java
 ```java
 package com.cma.spring.exceptiontest;
 public class PatientFactory {
	public Patient createPatient(int id, String name){
		System.out.println("Using factory class to create Patient");
		return new Patient(id, name);
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
	private int nationalId;
		private Address address;
	public Patient() {	
	}
	// getInstance method returns a patient object instance
	public static Patient getInstance(int id, String name){// pass constructor arguments through beans
		System.out.println("Creating patient using factory method :");	
		return new Patient(id,name);
	}
	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
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
	public void init(){
		System.out.println("Address Created :"+this);
	}
	public void destroy(){
		System.out.println("Address Destroyed");
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
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	default-init-method="init" default-destroy-method="destroy"><!--define globally-->


	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient"
	scope="singleton" init-method="onCreate" destroy-method="onDesteoy"
	
	factory-method="getInstance"> <!--call getInstance through factory method-->

	<constructor-arg value="5" name="id"></constructor-arg>
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property>
	<property name="address" ref="address"></property>
	</bean>
	<!--jist copy old bean-->
	<bean id="patient2" class="com.cma.spring.exceptiontest.Patient"
		scope="singleton" init-method="onCreate" destroy-method="onDesteoy"

		factory-method="createPatient" factory-bean="patientfactory">

		<constructor-arg value="5" name="id"></constructor-arg>
		<constructor-arg value="Rafid" name="name"></constructor-arg>
		<property name="nationalId">
			<value>12345</value>
		</property>
		<property name="address" ref="address"></property>
	</bean>


	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address">
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>
	<!--created new bean , the class name is patient factory -->
	<bean class="com.cma.spring.exceptiontest.PatientFactory"
		id="patientfactory">
	</bean>
	
</beans>
```





# Lacture 12
## Objective : Define properties using p-namespace, (just alternative way)

### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		Address address2 = (Address)context.getBean("address2");
		System.out.println( patient );
		System.out.println( address2 );
    ((ClassPathXmlApplicationContext)context).close();
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
	private int nationalId;
		private Address address;
	public Patient() {	
	}
	// getInstance method returns a patient object instance
	public static Patient getInstance(int id, String name){// pass constructor arguments through beans
		System.out.println("Creating patient using factory method :");	
		return new Patient(id,name);
	}
	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
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
	public void init(){
		System.out.println("Address Created :"+this);
	}
	public void destroy(){
		System.out.println("Address Destroyed");
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

	xmlns:p="http://www.springframework.org/schema/p" 

	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	default-init-method="init" default-destroy-method="destroy"><!--define globally-->

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient"
	scope="singleton" init-method="onCreate" destroy-method="onDesteoy"
	factory-method="getInstance"> <!--call getInstance through factory method-->
	<constructor-arg value="5" name="id"></constructor-arg>
	<constructor-arg value="cm" name="name"></constructor-arg>
	<property name="nationalId" value="1234"></property>
	<property name="address" ref="address"></property>
	</bean>

	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address">
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>

	<bean id="address2" 
	class="com.cma.spring.exceptiontest.Address" p:street="road no 5" p:postcode="1219">
	</bean>

</beans>
```




# Lacture 13
## Objective : Define properties using c-namespace.
##### we will see how to use reference beans through p-namespace
##### p-namespace use for properyies
##### c-namespace use for constructor arguments


### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		Address address2 = (Address)context.getBean("address2");
		System.out.println( patient );
		System.out.println( address2 );
    ((ClassPathXmlApplicationContext)context).close();
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
	private int nationalId;
		private Address address;
	public Patient() {	
	}
	// getInstance method returns a patient object instance
	public static Patient getInstance(int id, String name){// pass constructor arguments through beans
		System.out.println("Creating patient using factory method :");	
		return new Patient(id,name);
	}
	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
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
	public void init(){
		System.out.println("Address Created :"+this);
	}
	public void destroy(){
		System.out.println("Address Destroyed");
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
	xmlns:p="http://www.springframework.org/schema/p" 

	xmlns:c="http://www.springframework.org/schema/c"

	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	default-init-method="init" default-destroy-method="destroy"><!--define globally-->

	<bean id="patient" 
	class="com.cma.spring.exceptiontest.Patient"
	scope="singleton" init-method="onCreate" destroy-method="onDesteoy"
	factory-method="getInstance" p:address-ref="address"
		c:_0="6" 
		c:_1="Herry" ><!--use reference address beans through p-namespace-->
		<!--argument index-->

	<property name="nationalId" value="1234"></property>
	</bean>

	<bean id="address" 
	class="com.cma.spring.exceptiontest.Address">
	<constructor-arg name="street" value="rampura road 5"></constructor-arg>
	<constructor-arg name="postcode" value="1219"></constructor-arg>
	</bean>
	<bean id="address2" 
	class="com.cma.spring.exceptiontest.Address" p:street="road no 5" p:postcode="1219">
	</bean>

</beans>
```


# Lacture 14
## Objective : how initialize list through beans.xml
##### List is the property of collection class object

* set and List re pretty fine
* set elements never returns


### App.java
 ```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		System.out.println( patient );
		for (String name:patient.getEmargencyContactNumber()){
			System.out.println(name);
		}
    ((ClassPathXmlApplicationContext)context).close();
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
	private List<String> emargencyContactNumber;

	public Patient() {		
	}

	public void onCreate(){
		System.out.println("Patient created: "+this);
	}
	public void onDesteoy(){
		System.out.println("Patient destroyed ");
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
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
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}
	public List<String> getEmargencyContactNumber() {
		return emargencyContactNumber;
	}
	public void setEmargencyContactNumber(List<String> emargencyContactNumber) {
		this.emargencyContactNumber = emargencyContactNumber;
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
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:c="http://www.springframework.org/schema/c"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="patient" class="com.cma.spring.exceptiontest.Patient"
		scope="singleton" init-method="onCreate" destroy-method="onDesteoy"
		c:_0="6" c:_1="Herry">

	<property name="emargencyContactNumber">
		<list><!--property list-->
			<value>name 1</value>
			<value>name 2</value>
			<value>name 3</value>
			<value>name 4</value>
			<value>name 5</value>
			<value>name 1</value>
		</list>
	</property>
	</bean>
</beans>
```