

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



- [Awesome Java](#awesome-java)
    - [Lacture_11_part_1](#Lacture_11_part_1)
    - [Bean Mapping](#bean-mapping)
    - [Build](#build)

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
	scope="prototype"><!--here scope is not singleton its prototype , always return different object-->
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






# Lacture_11_part_1
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



# Lacture 15
## Objective : Setting List of beans 
##### How use referrence beans


### App.java
```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		for (EmargencyContact name:patient.getEmargencyContacts()){
			System.out.println(name);
		}
    ((ClassPathXmlApplicationContext)context).close();
    }
}

```

### EmargencyContact.java

```java
package com.cma.spring.exceptiontest;
public class EmargencyContact {
	private String name;
	private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "EmargencyContact [name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
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
	private List<EmargencyContact> emargencyContacts;// EmargencyContact type where object = emargencyContacts
	public Patient() {
		
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
	public List<EmargencyContact> getEmargencyContacts() {
		return emargencyContacts;
	}
	public void setEmargencyContacts(List<EmargencyContact> emargencyContacts) {
		this.emargencyContacts = emargencyContacts;
	}
}

```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" class="com.cma.spring.exceptiontest.Patient">
	<property name="emargencyContacts">
		<list>
			<ref bean="contact1" /><!--take referrence-->
			<ref bean="contact2" />
			<ref bean="contact3" />
			<ref bean="contact4" />
		</list>
	</property>
	</bean>
	

	<!--new bean-->
	<bean id="contact1"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abdullah"></property>
	<property name="phoneNumber" value="01717243358"></property>
	</bean>
	
	<bean id="contact2"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abida"></property>
	<property name="phoneNumber" value="01717243359"></property>
	</bean>	
	
	<bean id="contact3"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Rafid"></property>
	<property name="phoneNumber" value="01717243367"></property>
	</bean>	
	
	<bean id="contact4"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Khalid"></property>
	<property name="phoneNumber" value="01717243387"></property>
	</bean>	
</beans>

```

# Lacture 16
## Objective : Inner Beans

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
		Patient patient = (Patient)context.getBean("patient");
		System.out.println(patient);
		for (EmargencyContact name:patient.getEmargencyContacts()){
			System.out.println(name);
		}
    ((ClassPathXmlApplicationContext)context).close();
    }
}

```

### EmargencyContact.java

```java
package com.cma.spring.exceptiontest;
public class EmargencyContact {
	private String name;
	private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "EmargencyContact [name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
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
	private EmargencyContact criticalContact;
	private List<EmargencyContact> emargencyContacts;
	public Patient() {	
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public EmargencyContact getCriticalContact() {
		return criticalContact;
	}
	public void setCriticalContact(EmargencyContact criticalContact) {
		this.criticalContact = criticalContact;
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
		return "Patient [id=" + id + ", name=" + name + ", criticalContact="
				+ criticalContact + "]";
	}
	public List<EmargencyContact> getEmargencyContacts() {
		return emargencyContacts;
	}
	public void setEmargencyContacts(List<EmargencyContact> emargencyContacts) {
		this.emargencyContacts = emargencyContacts;
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="patient" class="com.cma.spring.exceptiontest.Patient">
	<property name="emargencyContacts">
		<list>
			<bean
				class="com.cma.spring.exceptiontest.EmargencyContact">
			<property name="name" value="Abid Khan"></property>
			<property name="phoneNumber" value="01717243358"></property>
			</bean>	<!-- inner beans -->	
		
			<ref bean="contact1" />
			<ref bean="contact2" />
			<ref bean="contact3" />
			<ref bean="contact4" />
		</list>
	</property>
	<property name="criticalContact">
		<bean
			class="com.cma.spring.exceptiontest.EmargencyContact">
		<property name="name" value="C M "></property>
		<property name="phoneNumber" value="01717666666"></property>
		</bean>	<!-- inner beans -->	
	
	</property>
	</bean>

	<bean id="contact1"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abdullah"></property>
	<property name="phoneNumber" value="01717243358"></property>
	</bean>
	
	<bean id="contact2"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abida"></property>
	<property name="phoneNumber" value="01717243359"></property>
	</bean>	
	
	<bean id="contact3"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Rafid"></property>
	<property name="phoneNumber" value="01717243367"></property>
	</bean>	
	
	<bean id="contact4"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Khalid"></property>
	<property name="phoneNumber" value="01717243387"></property>
	</bean>	
</beans>
```



# Lacture 17
## Objective : Setting Maps 

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	ContactBook contacts = (ContactBook)context.getBean("contactbook");
			System.out.println(contacts);
    ((ClassPathXmlApplicationContext)context).close();
    }
}

```


### ContactBook.java

```java
package com.cma.spring.exceptiontest;
import java.util.HashMap;
import java.util.Map;
public class ContactBook {
	private Map<String , EmargencyContact> contacts = new HashMap <String, EmargencyContact>();
	public Map<String, EmargencyContact> getContacts() {
		return contacts;
	}
	public void setContacts(Map<String, EmargencyContact> contacts) {
		this.contacts = contacts;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, EmargencyContact> contacts : contacts.entrySet() ){
			sb.append(contacts.toString()+"\n");
		}
		return sb.toString();
	}
}

```

### EmargencyContact.java

```java
package com.cma.spring.exceptiontest;
public class EmargencyContact {
	private String name;
	private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "EmargencyContact [name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
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
	private EmargencyContact criticalContact;
	private List<EmargencyContact> emargencyContacts;
	public Patient() {	
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public EmargencyContact getCriticalContact() {
		return criticalContact;
	}
	public void setCriticalContact(EmargencyContact criticalContact) {
		this.criticalContact = criticalContact;
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
		return "Patient [id=" + id + ", name=" + name + ", criticalContact="
				+ criticalContact + "]";
	}
	public List<EmargencyContact> getEmargencyContacts() {
		return emargencyContacts;
	}
	public void setEmargencyContacts(List<EmargencyContact> emargencyContacts) {
		this.emargencyContacts = emargencyContacts;
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="patient" class="com.cma.spring.exceptiontest.Patient">
	<property name="emargencyContacts">
		<list>
		<bean
			class="com.cma.spring.exceptiontest.EmargencyContact">
		<property name="name" value="Abid Khan"></property>
		<property name="phoneNumber" value="01717243358"></property>
		</bean>	<!-- inner beans -->	
				<ref bean="contact1" /><!--bean reference-->
				<ref bean="contact2" />
				<ref bean="contact3" />
				<ref bean="contact4" />
		</list>
	</property>
	<property name="criticalContact">
		<bean
			class="com.cma.spring.exceptiontest.EmargencyContact">
		<property name="name" value="C M "></property>
		<property name="phoneNumber" value="01717666666"></property>
		</bean>	<!-- inner beans -->	
	
	</property>
	</bean>

	<bean id="contact1"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abdullah"></property>
	<property name="phoneNumber" value="01717243358"></property>
	</bean>
	<bean id="contact2"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abida"></property>
	<property name="phoneNumber" value="01717243359"></property>
	</bean>	
	<bean id="contact3"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Rafid"></property>
	<property name="phoneNumber" value="01717243367"></property>
	</bean>	
	<bean id="contact4"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Khalid"></property>
	<property name="phoneNumber" value="01717243387"></property>
	</bean>	
	

	<!--Map with value-ref-->
	<bean id="contactbook"
		class="com.cma.spring.exceptiontest.ContactBook">
	<property name="contacts">
		<map>
			<entry key="first" value-ref="contact1"></entry>
			<entry key="second" value-ref="contact2"></entry>
			<entry key="third" value-ref="contact3"></entry>
			<entry key="forth" value-ref="contact4"></entry>
		</map>
	</property>
	</bean>
</beans>
```




# Lacture 18
## Objective : Use null to set a property

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){

    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	ContactBook contacts = (ContactBook)context.getBean("contactbook");
			System.out.println(contacts);
    ((ClassPathXmlApplicationContext)context).close();
    }
}
```



### ContactBook.java

```java
package com.cma.spring.exceptiontest;
import java.util.HashMap;
import java.util.Map;
public class ContactBook {
	private Map<String , EmargencyContact> contacts = new HashMap <String, EmargencyContact>();
	private Patient patient;//added patient element with getter & setter
	public ContactBook(){
		patient = new Patient(10, "Mainul");//initialize constructor
	}
	public Map<String, EmargencyContact> getContacts() {
		return contacts;
	}
	public void setContacts(Map<String, EmargencyContact> contacts) {
		this.contacts = contacts;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(patient == null ? "Patient is null\n": patient.toString()+"\n");//check condition
		for (Map.Entry<String, EmargencyContact> contacts : contacts.entrySet() ){
			sb.append(contacts.toString()+"\n");
		}
		return sb.toString();
	}
}
```

### EmargencyContact.java

```java
package com.cma.spring.exceptiontest;
public class EmargencyContact {
	private String name;
	private String phoneNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "EmargencyContact [name=" + name + ", phoneNumber="
				+ phoneNumber + "]";
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
	private EmargencyContact criticalContact;
	private List<EmargencyContact> emargencyContacts;
	public Patient() {	
	}
	public Patient(int id, String name) {
	this.id = id;
	this.name = name;
	}
	public EmargencyContact getCriticalContact() {
		return criticalContact;
	}
	public void setCriticalContact(EmargencyContact criticalContact) {
		this.criticalContact = criticalContact;
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
		return "Patient [id=" + id + ", name=" + name + ", criticalContact="
				+ criticalContact + "]";
	}
	public List<EmargencyContact> getEmargencyContacts() {
		return emargencyContacts;
	}
	public void setEmargencyContacts(List<EmargencyContact> emargencyContacts) {
		this.emargencyContacts = emargencyContacts;
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="patient" class="com.cma.spring.exceptiontest.Patient">
	<property name="emargencyContacts">
		<list>
		<bean
			class="com.cma.spring.exceptiontest.EmargencyContact">
		<property name="name" value="Abid Khan"></property>
		<property name="phoneNumber" value="01717243358"></property>
		</bean>	<!-- inner beans -->	
				<ref bean="contact1" />
				<ref bean="contact2" />
				<ref bean="contact3" />
				<ref bean="contact4" />
		</list>
	</property>
	<property name="criticalContact">
		<bean
			class="com.cma.spring.exceptiontest.EmargencyContact">
		<property name="name" value="C M "></property>
		<property name="phoneNumber" value="01717666666"></property>
		</bean>	<!-- inner beans -->
	</property>
	</bean>
	<bean id="contact1"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abdullah"></property>
	<property name="phoneNumber" value="01717243358"></property>
	</bean>
	<bean id="contact2"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Abida"></property>
	<property name="phoneNumber" value="01717243359"></property>
	</bean>	
	<bean id="contact3"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Rafid"></property>
	<property name="phoneNumber" value="01717243367"></property>
	</bean>	
	<bean id="contact4"
		class="com.cma.spring.exceptiontest.EmargencyContact">
	<property name="name" value="Khalid"></property>
	<property name="phoneNumber" value="01717243387"></property>
	</bean>	
	<bean id="contactbook"
		class="com.cma.spring.exceptiontest.ContactBook">
	<property name="contacts">
		<map>
			<entry key="first" value-ref="contact1"></entry>
			<entry key="second" value-ref="contact2"></entry>
			
			<entry key="third" value-ref="contact3"></entry>
			<entry key="forth" value-ref="contact4"></entry>
		</map>
	</property>
	<property name="patient"><null/></property><!--property is null-->
	</bean>
</beans>
```



# Lacture 19
## Objective : Autowiring by type

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Logger logger = (Logger) context.getBean("logger");
    	logger.writeConsole("Hello There");
    	logger.writeFile("Hi there");
    ((ClassPathXmlApplicationContext)context).close();
    }
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
public class Logger {
	private ConsoleWriter consoleWriter;
	private FileWriter fileWriter;
	public ConsoleWriter getConsoleWriter() {
		return consoleWriter;
	}
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public FileWriter getFileWriter() {
		return fileWriter;
	}
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}	
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="consoleWriter"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>
	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>
	<bean id="logger" class="com.cma.spring.exceptiontest.Logger"
		autowire="byType"><!--autowrite type byType, that access different types of bean based on id-->
	</bean>
</beans>
```


# Lacture 20
## Objective : Autowiring by Name

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Logger logger = (Logger) context.getBean("logger");
    	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
    	logger.writeFile("Hi there");		// writeFile method exist on Logger class
    ((ClassPathXmlApplicationContext)context).close();
    }
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
public class Logger {
	private LogWriter consoleWriter; //type LogWriter
	private LogWriter fileWriter;	// type LogWriter
	public LogWriter getConsoleWriter() {
		return consoleWriter;
	}
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() {
		return fileWriter;
	}
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}	
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="consoleWriter"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>
	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>
	<bean id="logger" class="com.cma.spring.exceptiontest.Logger"
		autowire="byName"><!--autowrite byName, that access different name of bean, based on name or id-->
	</bean>
</beans>
```


# Lacture 21
## Objective : Autowiring by Constructure

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Logger logger = (Logger) context.getBean("logger");
    	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
    	logger.writeFile("Hi there");		// writeFile method exist on Logger class
    ((ClassPathXmlApplicationContext)context).close();
    }
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
public class Logger {
	private LogWriter consoleWriter;
	private LogWriter fileWriter;
	public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {//constructor added
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}
	public LogWriter getConsoleWriter() { //type LogWriter
		return consoleWriter;
	}
	public void setConsoleWriter(LogWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() { // type LogWriter
		return fileWriter;
	}
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}	
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="consoleWriter"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>
	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>
	<bean id="logger" class="com.cma.spring.exceptiontest.Logger"
		autowire="constructor">
	</bean>
</beans>
```




# Lacture 22
## Objective : Default Autowiring

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Logger logger = (Logger) context.getBean("logger");
    	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
    	logger.writeFile("Hi there");		// writeFile method exist on Logger class
    ((ClassPathXmlApplicationContext)context).close();
    }
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
public class Logger {
	private ConsoleWriter consoleWriter;
	private FileWriter fileWriter;

	public LogWriter getConsoleWriter() { //type LogWriter
		return consoleWriter;
	}
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() { // type LogWriter
		return fileWriter;
	}
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}	
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	default-autowire="byType">

	<bean id="consoleWriter"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>
	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="whatever"
		class="com.cma.spring.exceptiontest.FileWriter"
		autowire-candidate="false">
	</bean>	
	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
</beans>
```


# Lacture 23
## Objective : Removed Autowiring ambiguity

### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
public class Logger {
	private ConsoleWriter consoleWriter;
	private FileWriter fileWriter;

	public LogWriter getConsoleWriter() { //type LogWriter
	return consoleWriter;
	}
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
	this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() { // type LogWriter
	return fileWriter;
	}
	public void setFileWriter(FileWriter fileWriter) {
	this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
	fileWriter.write(text);
	}
	public void writeConsole(String text) {
	consoleWriter.write(text);    
	}    
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
default-autowire="byType">


<bean id="consoleWriter"
class="com.cma.spring.exceptiontest.ConsoleWriter"
primary="true"><!-- removed Autowiring ambiguity -->
</bean>

<bean id="consoleWriter1"
class="com.cma.spring.exceptiontest.ConsoleWriter">
</bean>
<bean id="fileWriter"
class="com.cma.spring.exceptiontest.FileWriter">
</bean>

<bean id="whatever" class="com.cma.spring.exceptiontest.FileWriter"
autowire-candidate="false">
</bean>

<bean id="logger" 
class="com.cma.spring.exceptiontest.Logger">
</bean>
</beans>
```



# Lacture 24
## Objective : The Autowired Annotation
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;

public class Logger {
	//@Autowired //variable level auto wiring
	private ConsoleWriter consoleWriter;
	//@Autowired 
	private FileWriter fileWriter;
	@Autowired //conostructor level auto wiring
	public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}
	public LogWriter getConsoleWriter() {
		return consoleWriter;
	}
	//@Autowired //method level auto wiring
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() {
		return fileWriter;
	}
	//@Autowired
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
}

```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<bean id="consoleWriter"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>

	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
	<context:annotation-config></context:annotation-config>
</beans>
```



# Lacture 25
## Objective : Required Attribute of Autowired Annotation
### অবজেক্ট থাকলে ভালো না থাকলে সমস্যা নাই।
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;
public class FileWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java

package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;

public class Logger {
	//@Autowired //variable level auto wiring
	private ConsoleWriter consoleWriter;
	//@Autowired 
	private FileWriter fileWriter;
	
	public Logger() {
		
	}
	
	@Autowired(required = false) //conostructor level auto wiring
	public Logger(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getConsoleWriter() {
		return consoleWriter;
	}
	//@Autowired //method level auto wiring
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() {
		return fileWriter;
	}
	@Autowired
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		if (consoleWriter !=null)
		consoleWriter.write(text);	
	}
}


```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<bean id="consoleWriter"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>

	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
	<context:annotation-config></context:annotation-config>
</beans>
```


# Lacture 26
## Objective : Using Qualifiers
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
	public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("filewriter") //class level qualifier
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Logger {
	//@Autowired //variable level auto wiring
	private ConsoleWriter consoleWriter;
	//@Autowired 
	private LogWriter fileWriter;

	public LogWriter getConsoleWriter() {
		return consoleWriter;
	}
	@Autowired //method level auto wiring
	@Qualifier("consolewriter") //method level qualifier
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	public LogWriter getFileWriter() {
		return fileWriter;
	}
	@Autowired
	@Qualifier("filewriter")
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
}


```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<bean id="example"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>

	<bean id="mrBin"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	<qualifier value="consolewriter"></qualifier>
	</bean>


	<bean id="DemoWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
	<context:annotation-config></context:annotation-config>
</beans>
```


# Lacture 27
## Objective : Resource Annotation JSR 250
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
	public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;

public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
import javax.annotation.Resource;
public class Logger {

	private ConsoleWriter consoleWriter;
	private LogWriter fileWriter;

	@Resource(name="example")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}

	@Resource(name="DemoWriter")
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
}

```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<bean id="example"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>

	<bean id="mrBin"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	<qualifier value="consolewriter"></qualifier>
	</bean>


	<bean id="DemoWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
	<context:annotation-config></context:annotation-config>
</beans>
```


# Lacture 28
## Objective : init destroy method through annotation
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
	public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;

public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
import javax.annotation.*;
public class Logger {

	private ConsoleWriter consoleWriter;
	private LogWriter fileWriter;

	@Resource(name="example")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}

	@Resource(name="DemoWriter")
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
	@PostConstruct
	public void init() {
		System.out.println("beans created");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("beans destroyed");
	}
}

```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<bean id="example"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>

	<bean id="mrBin"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	<qualifier value="consolewriter"></qualifier>
	</bean>


	<bean id="DemoWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
	<context:annotation-config></context:annotation-config>
</beans>
```


# Lacture 29
## Objective : 29 Inject Annotation JSR 330
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
	public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;

public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
import javax.annotation.*;
import javax.inject.Inject;
import javax.inject.Named;
public class Logger {

	private ConsoleWriter consoleWriter;
	private LogWriter fileWriter;
	@Inject
	@Named(value="demoWriter2")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	@Inject
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
	@PostConstruct
	public void init() {
		System.out.println("beans created");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("beans destroyed");
	}
}

```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<bean id="demoWriter2"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>
	
	<bean id="consoleWriter2"
		class="com.cma.spring.exceptiontest.ConsoleWriter">
	</bean>


	<bean id="fileWriter"
		class="com.cma.spring.exceptiontest.FileWriter">
	</bean>

	<bean id="logger" 
		class="com.cma.spring.exceptiontest.Logger">
	</bean>
	<context:annotation-config></context:annotation-config>
</beans>
```


# Lacture 30
## Objective : Automatic Bean Discovery
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
	public static void main( String[] args ){
	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
	Logger logger = (Logger) context.getBean("logger");
	logger.writeConsole("Hello There"); // writeConsole method exist on Logger class
	logger.writeFile("Hi there");        // writeFile method exist on Logger class
	((ClassPathXmlApplicationContext)context).close();
	}
}
```

### LogWriter.java

```java
package com.cma.spring.exceptiontest;
public interface LogWriter {
	public void write(String text);
}
```

### ConsoleWriter.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements LogWriter {
	public void write(String text) {
	System.out.println("From console Writer : "+text);
	}
}
```

### FileWriter.java

```java
package com.cma.spring.exceptiontest;

import org.springframework.stereotype.Component;

@Component
public class FileWriter implements LogWriter {
	public void write(String text) {
		System.out.println("From file : "+text);
	}
}
```

### Logger.java

```java
package com.cma.spring.exceptiontest;
import javax.annotation.*;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
@Component
public class Logger {

	private ConsoleWriter consoleWriter;
	private LogWriter fileWriter;
	@Inject
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	@Inject
	@Named(value="filewriter")//filewriter
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	public void writeConsole(String text) {
		consoleWriter.write(text);	
	}
	@PostConstruct
	public void init() {
		System.out.println("beans created");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("beans destroyed");
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan><!--Scan All class object-->
</beans>
```



# Lacture 31
## Objective : Setting Property Values through Annotations
### App.java

```java
package com.cma.spring.exceptiontest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ){

    	ApplicationContext context = new ClassPathXmlApplicationContext("com/cma/spring/exceptiontest/beans/beans.xml");
    	Cat cat = (Cat) context.getBean("cat");
    	cat.speek();
    ((ClassPathXmlApplicationContext)context).close();
    }
}

```



### Cat.java

```java
package com.cma.spring.exceptiontest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Cat {
	private int id = 0;
	private String speec= "Dont feel like" ;
	@Autowired
	public void setId(@Value("1234")int id) {
		this.id = id;
	}
	@Autowired
	public void setSpeec(@Value("How are You")String speec) {
		this.speec = speec;
	}
	public void speek() {
		System.out.println(id+": "+speec);
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">


	<context:annotation-config></context:annotation-config>
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan><!--Scan All class object-->
</beans>
```


# Lacture 32
## Objective : Introducing SPEL
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
	private int id = 0;
	private String speech= "Dont feel like" ;
	@Autowired
	public void setId(@Value("1234")int id) {
		this.id = id;
	}
	@Autowired
	public void setSpeech(@Value("How are You")String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}

}

```

### RandomSpeech.java

```java
package com.cma.spring.exceptiontest;

import java.util.Random;

public class RandomSpeech {
	private static String[] texts = {
			"I will back",
			"Get out !",
			"I want you clothes, boots and byck",
			null
	};
	public String getText() {
		Random random = new Random();
		return texts[random.nextInt(texts.length)];
	}
}


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
	<!-- 
		<property name="id" value="30"></property>
		<property name="speech" value="My name is Abdullah"></property>
		
		 -->
		 <!-- spell and parse integer -->
		<property name="id" value="#{34+30}"></property>
		<property name="speech" value="#{random.getText()}"></property>
	</bean>
	<bean id="random"
		class="com.cma.spring.exceptiontest.RandomSpeech">
	
	</bean>
</beans>
```

# Lacture 33/1
## Objective : SPEL with xml
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
	private String id ;
	private String speech ;
	@Autowired
	public void setId(String id) {
		this.id = id;
	}
	@Autowired
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}

}
```

### RandomSpeech.java

```java
package com.cma.spring.exceptiontest;

import java.util.Random;

public class RandomSpeech {
	private static String[] texts = {
			"I will back",
			"Get out !",
			"I want you clothes, boots and byck",
			null
	};
	public String getText() {
		Random random = new Random();
		return texts[random.nextInt(texts.length)];
	}
}


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
	<!-- 
		<property name="id" value="30"></property>
		<property name="speech" value="My name is Abdullah"></property>
		
		 -->
		 <!-- spell and parse integer -->
		<property name="id" value="#{random.getText()?.length()}"></property><!---->
		<property name="speech" value="#{random.getText()}"></property>
	</bean>
	<bean id="random"
		class="com.cma.spring.exceptiontest.RandomSpeech">
	
	</bean>
</beans>
```

# Lacture 33/2
## Objective : SPEL with Annotation
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
	private String id ;
	private String speech ;
	@Autowired
	public void setId(@Value("#{randomSpeech.getText()?.length()}") String id) {
		this.id = id;
	}
	@Autowired
	public void setSpeech(@Value("#{randomSpeech.getText()}") String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}

}
```

### RandomSpeech.java

```java
package com.cma.spring.exceptiontest;

import java.util.Random;
import org.springframework.stereotype.Component;
@Component
public class RandomSpeech {
	private static String[] texts = {
			"I will back",
			"Get out !",
			"I want you clothes, boots and byck",
			null
	};
	public String getText() {
		Random random = new Random();
		return texts[random.nextInt(texts.length)];
	}
}


```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>

</beans>
```



# Lacture 34
## Objective : SPEL with Operator
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
	private String id ;
	private String speech ;
	@Autowired
	public void setId(@Value("#{randomSpeech.getText()?.length()}")String id) {
		this.id = id;
	}
	@Autowired
	//public void setSpeech(@Value("#{new java.util.Date().toString()}")String speech) {
	//public void setSpeech(@Value("#{T(Math).PI}")String speech) {
	public void setSpeech(@Value("#{T(Math).sin(T(Math).PI/4) lt 0.4 ? 'yes' : 'no'}")String speech) {
		this.speech = speech;
	}
	public void speek() {
		System.out.println(id+": "+speech);
	}

}

```

### RandomSpeech.java

```java
package com.cma.spring.exceptiontest;

import java.util.Random;
import org.springframework.stereotype.Component;
@Component
public class RandomSpeech {
	private static String[] texts = {
			"I will back",
			"Get out !",
			"I want you clothes, boots and byck",
			null
	};
	public String getText() {
		Random random = new Random();
		return texts[random.nextInt(texts.length)];
	}
}


```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	<context:component-scan
		base-package="com.cma.spring.exceptiontest">
	</context:component-scan>

</beans>
```



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
	private String id = "123";
	private String speech ="Hi cm" ;

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
    		
    		Notice notice1 = new Notice("Abdullah", "cm@gmail.com", "hi Cm How are you?");
    		//noticesDAO.create(notice1);
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




# Lacture 51
## Objective : Adding a Controller
### NoticeController.java

```java
package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	@RequestMapping("/")
	public String showHome() {
		System.out.println("From notice controller");
		return "home";
	}
}
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
</web-app>
```


### pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.abdullah.khan</groupId>
  <artifactId>notices</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.7.0</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
          <warSourceDirectory>WebContent</warSourceDirectory>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <dependencies>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-core</artifactId>
  		<version>3.2.11.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-beans</artifactId>
  		<version>3.2.11.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-context</artifactId>
  		<version>3.2.11.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-jdbc</artifactId>
  		<version>3.2.11.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-web</artifactId>
  		<version>3.2.11.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-webmvc</artifactId>
  		<version>3.2.11.RELEASE</version>
  	</dependency>
  </dependencies>
</project>
```



# Lacture 52
## Objective : View Resolvers
### NoticeController.java

```java
package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	@RequestMapping("/")
	public String showHome() {
		//System.out.println("From notice controller");
		return "home";
	}
}
```
### home.jsp

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
		hello world
	</body>
</html>
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
</web-app>
```



# Lacture 53
## Objective : Adding Data to Session
# Scoope Session wise
### NoticeController.java

```java
package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	@RequestMapping("/")
	public String showHome(HttpSession session) {//session object is created
		session.setAttribute("name", "C M Abdullah Khan");
		return "home";
	}
}
```
### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body><!--retrive data from controller-->
		<p>Session result is : <%= session.getAttribute("name") %></p>
	</body>
</html>
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
</web-app>
```


# Lacture 54
## Objective : Using Spring Data Models
# Scope Request wise
### NoticeController.java

```java
package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {// Smart way
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "tipu sultan");
		return "home";
	}
	/***
public ModelAndView showHome() {// old schoolway
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "Mr Khan");
		return mv;
	}
	 * **/
}
```
### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body><!--retrive data from controller-->
		<p>Request:  <%= request.getAttribute("name") %></p><br><!-- old school way -->
		Request: ${name}<!-- Smart way -->
	</body>
</html>
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
</web-app>
```







# Lacture 55
## Objective : Using JSTL
# Scope Request wise
### NoticeController.java

```java
package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {// Smart way
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "tipu sultan");
		return "home";
	}
	/***
public ModelAndView showHome() {// old schoolway
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "Mr Khan");
		return mv;
	}
	 * **/
}
```
### home.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body><!--retrive data from controller-->
		Request: ${name}<!-- Smart way -->
		<br>
		Request : (Using JSTL) <c:out value="${name}"></c:out>		
	</body>
</html>
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
</web-app>
```

# Lacture 56
## Objective : Configuring a JNDI DataSource
### NoticeController.java


1. context.xml (Tomcat server)
	<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
	maxActive="100" maxIdle="30" maxWait="10000"
	username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/springtutorial"/>

```java
package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {// Smart way
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "tipu sultan");
		return "home";
	}
	/***
public ModelAndView showHome() {// old schoolway
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "Mr Khan");
		return mv;
	}
	 * **/
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
	<body><!--retrive data from controller-->
		Request: ${name}<!-- Smart way -->
		<br>
		Request : (Using JSTL) <c:out value="${name}"></c:out>	
		<sql:query var="rs" dataSource="jdbc/spring">
			select id, name, email, text from notices
		</sql:query>
		<!--For each loop-->
		<c:forEach var="row" items="${rs.rows}">
		    Name: ${row.name}<br/>
		    Email: ${row.email}<br/>
		</c:forEach>	
	</body>
</html>
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
</web-app>
```






# Lacture 57-58
## Objective : Loading Bean Containers with ContextLoaderListener


1. context.xml (Tomcat server)
	<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
	maxActive="100" maxIdle="30" maxWait="10000"
	username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/springtutorial"/>

### NoticeController.java

```java
package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {// Smart way
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "tipu sultan");
		return "home";
	}
	/***
public ModelAndView showHome() {// old schoolway
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "Mr Khan");
		return mv;
	}
	 * **/
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
	//@Autowired
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
	<body><!--retrive data from controller-->
		Request: ${name}<!-- Smart way -->
		<br>
		Request : (Using JSTL) <c:out value="${name}"></c:out>	
		<sql:query var="rs" dataSource="jdbc/spring">
			select id, name, email, text from notices
		</sql:query>
		<!--For each loop-->
		<c:forEach var="row" items="${rs.rows}">
		    Name: ${row.name}<br/>
		    Email: ${row.email}<br/>
		</c:forEach>	
	</body>
</html>
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
		<param-value>classpath:com/spring/web/config/dao-context.xml</param-value>
	</context-param>
</web-app>
```




# Lacture 59
## Objective : Creating DataSource Bean


1. context.xml (Tomcat server)

	<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
	maxActive="100" maxIdle="30" maxWait="10000"
	username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/springtutorial"/>

### NoticeController.java

```java
package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {// Smart way
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "tipu sultan");
		return "home";
	}
	/***
public ModelAndView showHome() {// old schoolway
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "Mr Khan");
		return mv;
	}
	 * **/
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
	<body><!--retrive data from controller-->
		Request: ${name}<!-- Smart way -->
		<br>
		Request : (Using JSTL) <c:out value="${name}"></c:out>	
		<sql:query var="rs" dataSource="jdbc/spring">
			select id, name, email, text from notices
		</sql:query>
		<!--For each loop-->
		<c:forEach var="row" items="${rs.rows}">
		    Name: ${row.name}<br/>
		    Email: ${row.email}<br/>
		</c:forEach>	
	</body>
</html>
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
		<param-value>classpath:com/spring/web/config/dao-context.xml</param-value>
	</context-param>
</web-app>
```



# Lacture 60
## Objective : Adding a Service Layer


1. context.xml (Tomcat server)

	<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
	maxActive="100" maxIdle="30" maxWait="10000"
	username="root" password="rootcm" driverClassName="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/springtutorial"/>

### NoticeController.java

```java
package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
		NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@RequestMapping("/")
	public String showHome(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "home";
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
	<body><!--retrive data from controller-->
<c:forEach var="notice" items="${notices}">
    ID: ${notice.id}<br/>
    Name: ${notice.name}<br/>
    Email: ${notice.email}<br/>
    Text: ${notice.text}<br/>
</c:forEach>	
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





# Lacture 61
## Objective : Adding a new controller


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

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
	
	NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@RequestMapping("/shownotice")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "shownotice";
	}

	@RequestMapping("/createnotice")
	public String createNotice() {
		return "createnotice";
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




# Lacture 62
## Objective : Getting URL Parameters


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

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
	
	NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@RequestMapping("/shownotice")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "shownotice";
	}

	@RequestMapping("/createnotice")
	public String createNotice() {
		return "createnotice";
	}

	//http://localhost:8081/notices/test?id=100
	//Getting URL Parameters
	@RequestMapping("/test")
	public String showTest(Model model , @RequestParam("id")String id) {
		System.out.println("ID is : "+id);
		return "home";
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






# Lacture 74
## Objective : Base project for working with Aspects

#### Dependencies

	 <dependencies>
	  	<dependency>
	  		<groupId>org.springframework</groupId>
	  		<artifactId>spring-core</artifactId>
	  		<version>3.2.12.RELEASE</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.springframework</groupId>
	  		<artifactId>spring-beans</artifactId>
	  		<version>3.2.12.RELEASE</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.springframework</groupId>
	  		<artifactId>spring-context</artifactId>
	  		<version>3.2.12.RELEASE</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.springframework</groupId>
	  		<artifactId>spring-aspects</artifactId>
	  		<version>3.2.12.RELEASE</version>
	  	</dependency>
	  </dependencies>

### App.java

```java
package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		camera.snap();
		context.close();
	}
}

```

### Camera.java

```java
package com.spring.aop;

public class Camera {
	public void snap() {
		System.out.println("SNAP");
	}
}

```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="camera" class="com.spring.aop.Camera"></bean>
</beans>
```


# Lacture 75
## Objective : A Simple Aspect Example 

### App.java

```java
package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		camera.snap();
		context.close();
	}
}

```

### Camera.java

```java
package com.spring.aop;

public class Camera {
	public void snap() {
		System.out.println("SNAP");
	}
}

```



### Logger.java

```java
package com.spring.aop;

public class Logger {
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}
}
```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">


	<bean id="camera" class="com.spring.aop.Camera"></bean>
	<bean id="logger" class="com.spring.aop.Logger"></bean> 
	<aop:config>
		<aop:pointcut
			expression="execution(void com.spring.aop.Camera.snap())"
			id="camerasnap" />
		<aop:aspect ref="logger" id="loggeraspect">
			<aop:before method="aboutToTakePhoto"
				pointcut-ref="camerasnap" />
		</aop:aspect>
	</aop:config>
</beans>
```



# Lacture 76
## Objective : Annotation Based Aspects 

### App.java

```java
package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		camera.snap();
		context.close();
	}
}

```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Camera {
	public void snap() {
		System.out.println("SNAP");
	}
}
```



### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Before("execution( void com.spring.aop.Camera.snap())")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}
}

```
### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
<!-- 
	<bean id="camera" class="com.spring.aop.Camera"></bean>
	<bean id="logger" class="com.spring.aop.Logger"></bean> 
 -->

<!-- 
	<aop:config>
		<aop:pointcut
			expression="execution( void com.spring.aop.Camera.snap())"
			id="camerasnap" />
		<aop:aspect ref="logger" id="loggeraspect">
			<aop:before method="aboutToTakePhoto"
				pointcut-ref="camerasnap" />
		</aop:aspect>
	</aop:config>
 -->
 
 <!-- 
 	<aop:config>
		
		<aop:aspect ref="logger" id="loggeraspect">
			<aop:before method="aboutToTakePhoto"
				pointcut="execution( void com.spring.aop.Camera.snap())" />
		</aop:aspect>
	</aop:config>
	
	 -->
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>

```




# Lacture 77
## Objective : Wildcards in Pointcut Expressions

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
About to take photo
Doing something related to camera......
SNAP
About to take photo
Doing something related to camera......
SNAP!! with exposer2000
About to take photo
Doing something related to camera......
SNAP!! with Photo name : Hi Cm its your campus 
About to take photo
Doing something related to camera......
SNAP!! Night mode .... 
Doing something related to camera......
Zoom lens : 3
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();
		lens.zoom(3);
		context.close();
	}
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Camera {
	public void snap() {
		System.out.println("SNAP");
	}
	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer"+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
}

```



### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	//@Pointcut("execution( void com.spring.aop.Camera.snap(..))")
	//@Pointcut("execution( *  com.spring.aop.Camera.snap(..))")
	//@Pointcut("execution( *  com.spring.aop.Camera.*(..))")
	@Pointcut("execution( *  com.spring.aop.Camera.*(..))")
	//@Pointcut("execution( *  *.*(..))")
	public void cameraSnap() {
		
	}
	@Pointcut("execution( *  *.*(..))")
	public void cameraActivity() {
		
	}
	
	//@Before("execution( void com.spring.aop.Camera.snap())")
	@Before("cameraSnap()")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}
	@Before("cameraActivity()")
	public void cameraRelatedActivity() {
		System.out.println("Doing something related to camera......");
	}
}
```

### Lens.java

###### com.spring.aop.accessories

```java
package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
public class Lens {
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
<!-- 
	<bean id="camera" class="com.spring.aop.Camera"></bean>
	<bean id="logger" class="com.spring.aop.Logger"></bean> 
 -->

<!-- 
	<aop:config>
		<aop:pointcut
			expression="execution( void com.spring.aop.Camera.snap())"
			id="camerasnap" />
		<aop:aspect ref="logger" id="loggeraspect">
			<aop:before method="aboutToTakePhoto"
				pointcut-ref="camerasnap" />
		</aop:aspect>
	</aop:config>
 -->
 
 <!-- 
 	<aop:config>
		
		<aop:aspect ref="logger" id="loggeraspect">
			<aop:before method="aboutToTakePhoto"
				pointcut="execution( void com.spring.aop.Camera.snap())" />
		</aop:aspect>
	</aop:config>
	
	 -->
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>

```



# Lacture 78
## Objective : Advice Types - After, Around, and Others

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Around advice (before)......
About to take photo
SNAP
Around advice ......by by....
Around advice (after)......
After advice......
After Returning......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		try {
			camera.snap();
		} catch (Exception e) {
			System.out.println(e.getMessage()  );
		}
		context.close();
	}
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Camera {
	public void snap() throws Exception{
		System.out.println("SNAP");
		
		throw new Exception("by by....");
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("execution( void com.spring.aop.Camera.snap(..))")

	public void cameraSnap() {
		
	}

	@Before("cameraSnap()")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}

	
	@After("cameraSnap()")
	public void afterAdvice() {
		System.out.println("After advice......");
	}
	
	// if method is executed normally
	@AfterReturning("cameraSnap()")
	public void afterReturning() {
		System.out.println("After Returning......");
	}	
	
	@AfterThrowing("cameraSnap()")
	public void afterThrowing() {
		System.out.println("After Throwing......");
	}	
	
	@Around("cameraSnap()")
	public void aroundAdvice(ProceedingJoinPoint p) {
		System.out.println("Around advice (before)......");
		
		try {
			p.proceed();
		} catch (Throwable e) {
			System.out.println("Around advice ......"+e.getMessage());
		}
		
		System.out.println("Around advice (after)......");
	}	
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>

```




# Lacture 79
## Objective : Proxies, Interfaces and Aspects

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Getting object of camera class com.sun.proxy.$Proxy11
Camera is instance of camera class : true
Around advice (before)......
About to take photo
SNAP
Around advice ......by by....
Around advice (after)......
After advice......
After Returning......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		//Object camera = context.getBean("camera");
		System.out.println("Getting object of camera "+camera.getClass());
		System.out.println("Camera is instance of ICamera class : "+(camera instanceof ICamera));
		
		
		try {
			camera.snap();
		} catch (Exception e) {
			System.out.println(e.getMessage()  );
		}
		context.close();
	}
}
```


### PhotoSnapper.java

```java
package com.spring.aop;

public interface PhotoSnapper {

}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	void snap() throws Exception;

}
```


### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
//public class Camera implements PhotoSnapper{
public class Camera implements PhotoSnapper, ICamera{
//	public Camera() {
//		System.out.println("From Camera constructor.......");
//	}
	
	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	public void snap() throws Exception{
		System.out.println("SNAP");
		throw new Exception("by by....");
	}

}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("execution( void com.spring.aop.Camera.snap(..))")

	public void cameraSnap() {
		
	}

	@Before("cameraSnap()")
	public void aboutToTakePhoto() {
		System.out.println("About to take photo");
	}

	
	@After("cameraSnap()")
	public void afterAdvice() {
		System.out.println("After advice......");
	}
	
	// if method is executed normally
	@AfterReturning("cameraSnap()")
	public void afterReturning() {
		System.out.println("After Returning......");
	}	
	
	@AfterThrowing("cameraSnap()")
	public void afterThrowing() {
		System.out.println("After Throwing......");
	}	
	
	
	@Around("cameraSnap()")
	public void aroundAdvice(ProceedingJoinPoint p) {
		System.out.println("Around advice (before)......");
		
		try {
			p.proceed();
		} catch (Throwable e) {
			System.out.println("Around advice ......"+e.getMessage());
		}
		
		System.out.println("Around advice (after)......");
	}	
}
```



### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```



# Lacture 80
## Objective : Within Pointcut Designator

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Before advice
SNAP
Before advice
SNAP!! with exposer2000
Before advice
SNAP!! with Photo name : Hi Cm its your campus 
Before advice
Car engine started......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		Camera camera = (Camera)context.getBean("camera");
		Car car = (Car) context.getBean("car");
		camera.snap();
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		
		car.start();
		context.close();
	}
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera {

	public void snap(){
		System.out.println("SNAP");
	}
	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer"+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("within( com.spring.aop.*)")
//@Pointcut("within( com.spring.aop.Camera)")
	public void cameraSnap() {
		
	}

	@Before("cameraSnap()")
	public void aboutToTakePhoto() {
		System.out.println("Before advice");
	}

}
```


### Car.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Car {
	public void start() {
		System.out.println("Car engine started......");
	}

}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```



# Lacture 81
## Objective : This and Target designators

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
*********** Within Demo
*********** Target Demo
*********** This Demo
SNAP
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		camera.snap();

		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	void snap();

}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	public void snap(){
		System.out.println("SNAP");
	}

}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("within( com.spring.aop.*)")
//@Pointcut("within( com.spring.aop.Camera)")
	public void withinDemo() {
		
	}

	@Pointcut("target( com.spring.aop.Camera)")
	public void targetDemo() {
			
	}
	@Pointcut("this( com.spring.aop.ICamera)")
	public void thisDemo() {
			
	}	
	@Before("withinDemo()")
	public void aboutToTakePhoto() {
		System.out.println("*********** Within Demo");
	}
	@Before("targetDemo()")
	public void targetBeforeDemo() {
		System.out.println("*********** Target Demo");
	}

	@Before("thisDemo()")
	public void thisBeforeDemo() {
		System.out.println("*********** This Demo");
	}
}
```



### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```



# Lacture 82
## Objective : Matching Subpackages

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
*********** Before Demo****************
SNAP
*********** Before Demo****************
Zoom lens : 500
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		lens.zoom(500);

		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	void snap();

}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	public void snap(){
		System.out.println("SNAP");
	}

}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("within(@org.springframework.stereotype.Component com.spring..*)")
	public void somePointCut() {
		
	}
	
	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}

}
```


#### New Package

### Lens.java

```java
package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
public class Lens {
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```




# Lacture 81
## Objective : This and Target designators

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
*********** Within Demo
*********** Target Demo
*********** This Demo
SNAP
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		camera.snap();

		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	void snap();

}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	public void snap(){
		System.out.println("SNAP");
	}

}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	@Pointcut("within( com.spring.aop.*)")
//@Pointcut("within( com.spring.aop.Camera)")
	public void withinDemo() {
		
	}

	@Pointcut("target( com.spring.aop.Camera)")
	public void targetDemo() {
			
	}
	@Pointcut("this( com.spring.aop.ICamera)")
	public void thisDemo() {
			
	}	
	@Before("withinDemo()")
	public void aboutToTakePhoto() {
		System.out.println("*********** Within Demo");
	}
	@Before("targetDemo()")
	public void targetBeforeDemo() {
		System.out.println("*********** Target Demo");
	}

	@Before("thisDemo()")
	public void thisBeforeDemo() {
		System.out.println("*********** This Demo");
	}
}
```



### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```



# Lacture 83
## Objective : Annotation Specific PCDs

### App.java

```java
package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
SNAP
SNAP!! with exposer : 2000
SNAP!! with Photo name : Hi Cm its your campus 
SNAP!! Night mode .... 
Zoom lens : 500
*********** Before Demo****************
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();
		lens.zoom(500);
		
		Car car = (Car) context.getBean("car");
		camera.snapCar(car);
		
		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	void snap();
	
	void snap(int exposer);
	String snap(String exposer) ;
	void snapNightTime();
	void snapCar(Car car);
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {
	//@Pointcut("within(@org.springframework.stereotype.Component com.spring..*)")
	
	//only for Deprecated annotation
	//@Pointcut("within(@Deprecated com.spring..*)")
	
	// point all Component annotation.....


	
//	@Pointcut("within(@org.springframework.stereotype.Component com.spring..*)")
//	//@Pointcut("within( com.spring.aop.Camera)")
//	public void somePointCut() {	
//	}
//	
//	@Before("somePointCut()")
//	public void somePointCutPhoto() {
//		System.out.println("*********** Before Demo****************");
//	}

	
	
//	@Pointcut("@target(org.springframework.stereotype.Component)")
//	public void somePointCut() {	
//	}
//
//	@Before("somePointCut()")
//	public void somePointCutPhoto() {
//		System.out.println("*********** Before Demo****************");
//	}	

	
	
//	@Pointcut("@annotation(java.lang.Deprecated)")
//	public void somePointCut() {	
//	}
//
//	@Before("somePointCut()")
//	public void somePointCutPhoto() {
//		System.out.println("*********** Before Demo****************");
//	}	
	
	
	@Pointcut("@args(org.springframework.stereotype.Component)")
	public void somePointCut() {	
	}

	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}
}
```


#### New Package

### Lens.java

```java
package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class Lens {
	
	@Deprecated
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```


# Lacture 84
## Objective : The Bean PCD

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
*********** Before Demo****************
SNAP
*********** Before Demo****************
SNAP!! with exposer : 2000
*********** Before Demo****************
SNAP!! with Photo name : Hi Cm its your campus 
*********** Before Demo****************
SNAP!! Night mode .... 
*********** Before Demo****************
Zoom lens : 500
*********** Before Demo****************
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		camera.snap();
		
		camera.snap(2000);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();
		lens.zoom(500);
		
		Car car = (Car) context.getBean("car");
		camera.snapCar(car);
		
		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	void snap();
	
	void snap(int exposer);
	String snap(String exposer) ;
	void snapNightTime();
	void snapCar(Car car);
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	//@Pointcut("bean(camera)")
	@Pointcut("bean(*e*)")
	public void somePointCut() {	
	}

	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}	

}
```


#### New Package

### Lens.java

```java
package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class Lens {
	
	@Deprecated
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```

# Lacture 85
## Objective : The Args PCD

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
*********** Before Demo****************
SNAP
SNAP!! with exposer : 2000
*********** Before Demo****************
SNAP!! with exposer : 1.8
SNAP!! with exposer : 500 apature1.8
SNAP!! with Photo name : Hi Cm its your campus 
SNAP!! Night mode .... 
Car engine started......
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");

		camera.snap();
		camera.snap(2000);
		camera.snap(1.8);
		camera.snap(500, 1.8);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();

		
		Car car = (Car) context.getBean("car");
		car.start();
		camera.snapCar(new Car());
		
		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	public abstract void snap();
	public abstract void snap(int exposer);
	public abstract String snap(String exposer) ;
	public abstract void snapNightTime();
	public abstract void snapCar(Car car);
	public abstract void snap(double d);
	public abstract void snap(int i, double d);
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}

	@Override
	public void snap(double exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
		
	}

	@Override
	public void snap(int i, double d) {
		System.out.println("SNAP!! with exposer : "+i+" apature"+d);
		
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	//only for no arguments
	//@Pointcut("args()")
	
	// only int
	//@Pointcut("args(int)")
	
	//only of two parameter given blew
	//@Pointcut("args(int,double)")
	
	//int wildcurt
	//@Pointcut("args(int,*)")
	
	//define full qualified domain manme Object
	//@Pointcut("args(com.spring.aop.Car)")
	
	// after int zero to any number of arguments
	//@Pointcut("args(int, ..)")

	// at first anything at the end double, if any int get At the end it will cust automatically
	//@Pointcut("args(.., double)")
	
	//we can fix this issue by using Double class
	@Pointcut("args(Double)")
	public void somePointCut() {	
	}
	
	@Before("somePointCut()")
	public void somePointCutPhoto() {
		System.out.println("*********** Before Demo****************");
	}	

}

```


#### New Package

### Lens.java

```java
package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class Lens {
	
	@Deprecated
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```


# Lacture 86
## Objective : Getting Target Method Arguments

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
*********** Before Demo****************
SNAP
*********** Before Demo****************
Args :2000
SNAP!! with exposer : 2000
*********** Before Demo****************
Args :1.8
SNAP!! with exposer : 1.8
*********** Before Demo****************
Args :500
Args :1.8
SNAP!! with exposer : 500 apature1.8
*********** Before Demo****************
Args :Hi Cm its your campus 
SNAP!! with Photo name : Hi Cm its your campus 
*********** Before Demo****************
SNAP!! Night mode .... 
Car engine started......
*********** Before Demo****************
Args :com.spring.aop.Car@48aaecc3
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");

		camera.snap();
		camera.snap(2000);
		camera.snap(1.8);
		camera.snap(500, 1.8);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();

		
		Car car = (Car) context.getBean("car");
		car.start();
		camera.snapCar(new Car());
		
		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	public abstract void snap();
	public abstract void snap(int exposer);
	public abstract String snap(String exposer) ;
	public abstract void snapNightTime();
	public abstract void snapCar(Car car);
	public abstract void snap(double d);
	public abstract void snap(int i, double d);
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}

	@Override
	public void snap(double exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
		
	}

	@Override
	public void snap(int i, double d) {
		System.out.println("SNAP!! with exposer : "+i+" apature"+d);
		
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	@Pointcut("target(com.spring.aop.Camera)")
	public void somePointCut() {	
	}
	
	@Before("somePointCut()")
	public void somePointCutPhoto(JoinPoint jp) {
		System.out.println("*********** Before Demo****************");
		
		for (Object ob : jp.getArgs()) {
			System.out.println("Args :" + ob);
		}
	}	

}
```


#### New Package

### Lens.java

```java
package com.spring.aop.accessories;

import org.springframework.stereotype.Component;

@Component
@Deprecated
public class Lens {
	
	@Deprecated
	public void zoom(int factor) {
		System.out.println("Zoom lens : "+factor);
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```



# Lacture 87
## Objective : Getting Arguments using Args 

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
SNAP
SNAP!! with exposer : 2000
SNAP!! with exposer : 1.8
*********** Before Demo****************
exposure : 500 ,aperture : 1.800000
SNAP!! with exposer : 500 apature1.8
SNAP!! with Photo name : Hi Cm its your campus 
SNAP!! Night mode .... 
Car engine started......
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");

		camera.snap();
		camera.snap(2000);
		camera.snap(1.8);
		camera.snap(500, 1.8);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();

		
		Car car = (Car) context.getBean("car");
		car.start();
		camera.snapCar(new Car());
		
		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	public abstract void snap();
	public abstract void snap(int exposer);
	public abstract String snap(String exposer) ;
	public abstract void snapNightTime();
	public abstract void snapCar(Car car);
	public abstract void snap(double d);
	public abstract void snap(int i, double d);
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}

	@Override
	public void snap(double exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
		
	}

	@Override
	public void snap(int i, double d) {
		System.out.println("SNAP!! with exposer : "+i+" apature"+d);
		
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	@Pointcut("args(exposure ,aperture)")
	public void somePointCut(int exposure ,double aperture) {	
	}
	
	@Before("somePointCut(exposure ,aperture)")
	public void somePointCutPhoto(int exposure ,double aperture) {
		System.out.println("*********** Before Demo****************");
		System.out.printf("exposure : %d ,aperture : %f\n", exposure ,aperture);

	}	

}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```



# Lacture 88
## Objective : Combining Pointcuts

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.accessories.Lens;
/***
SNAP
*********** after advice running****************
SNAP!! with exposer : 2000
SNAP!! with exposer : 1.8
*********** Before Demo****************
exposure : 500 ,aperture : 1.800000
SNAP!! with exposer : 500 apature1.8
SNAP!! with Photo name : Hi Cm its your campus 
SNAP!! Night mode .... 
Car engine started......
Snapping Car......
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		ICamera camera = (ICamera)context.getBean("camera");

		camera.snap();
		camera.snap(2000);
		camera.snap(1.8);
		camera.snap(500, 1.8);
		camera.snap("Hi Cm its your campus ");
		camera.snapNightTime();

		
		Car car = (Car) context.getBean("car");
		car.start();
		camera.snapCar(new Car());
		
		context.close();
	}
}
```

### ICamera.java

```java
package com.spring.aop;

public interface ICamera {

	public abstract void snap();
	public abstract void snap(int exposer);
	public abstract String snap(String exposer) ;
	public abstract void snapNightTime();
	public abstract void snapCar(Car car);
	public abstract void snap(double d);
	public abstract void snap(int i, double d);
}
```

### Camera.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")

public class Camera implements ICamera {

	/* (non-Javadoc)
	 * @see com.spring.aop.ICamera#snap()
	 */
	@Override
	@Deprecated
	public void snap(){
		System.out.println("SNAP");
	}

	public void snap(int exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
	}

	public String snap(String exposer) {
		System.out.println("SNAP!! with Photo name : "+exposer);
		return exposer;
	}
	
	public void snapNightTime() {
		System.out.println("SNAP!! Night mode .... ");
	}
	
	public void snapCar(Car car) {
		System.out.println("Snapping Car......");
	}

	@Override
	public void snap(double exposer) {
		System.out.println("SNAP!! with exposer : "+exposer);
		
	}

	@Override
	public void snap(int i, double d) {
		System.out.println("SNAP!! with exposer : "+i+" apature"+d);
		
	}
}
```

### Logger.java

```java
package com.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {

	@Pointcut("args(exposure ,aperture)")
	public void somePointCut(int exposure ,double aperture) {	
	}
	@Pointcut("target(com.spring.aop.Camera)")
	public void targetCamera() {	
	}
	
	
	// object within camera class with two parameter
	@Before("targetCamera() && somePointCut(exposure ,aperture)")
	public void somePointCutPhoto(int exposure ,double aperture) {
		System.out.println("*********** Before Demo****************");
		System.out.printf("exposure : %d ,aperture : %f\n", exposure ,aperture);
	}
	@After("within(com.spring.aop.*) && @annotation(Deprecated)")
	public void someAfterdvice() {
		System.out.println("*********** after advice running****************");
	}
}
```


### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```


# Lacture 89
## Objective : Adding functionality with Aspect

### App.java

```java
 package com.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/***
Machine Starting.....
Running....
Blending........
Completed....
Machine Starting.....
Running....
Fan is activate at level : 5
Completed....
 * **/
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/beans.xml");
		IBlender blender = (IBlender)context.getBean("blender");
		
		((IMachine)blender).start();
		blender.blend();
		
		IFan fan = (IFan)context.getBean("fan");
		((IMachine)fan).start();
		fan.activate(5);
		
		context.close();
	}
}
```

### IMachine.java

```java
package com.spring.aop;

public interface IMachine {
	public void start();

}
```

### IBlender.java

```java
package com.spring.aop;

public interface IBlender {
	public void blend();

}
```
### IFan.java

```java
package com.spring.aop;

public interface IFan {

	void activate(int level);

}
```
### Machine.java

```java
package com.spring.aop;

public class Machine implements IMachine{

	@Override
	public void start() {
		System.out.println("Machine Starting.....");	
	}
}
```

### Blender.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Blender implements IBlender {

	@Override
	public void blend() {
		System.out.println("Blending........");	
	}
}
```
### Fan.java

```java
package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Fan implements IFan {
	/* (non-Javadoc)
	 * @see com.spring.aop.IFan#activate(int)
	 */
	@Override
	public void activate(int level) {
		System.out.println("Fan is activate at level : "+level);	
	}
}
```
### MachineAspect.java

```java
package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MachineAspect {
	
	@DeclareParents(value="com.spring.aop.*", defaultImpl = com.spring.aop.Machine.class)
	private IMachine machine;
	
	@Around("within(com.spring.aop.*)")
	public void runMachine(ProceedingJoinPoint jp) {
		System.out.println("Running....");
		try {
			jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed....");
	}
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
 	<context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.spring.aop"></context:component-scan>
	<context:component-scan base-package="com.spring.aop.accessories"></context:component-scan>	
	<aop:aspectj-autoproxy proxy-target-class="false"></aop:aspectj-autoproxy>

	<!-- <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy> -->	
</beans>

```





# Lacture 90
## Objective : Servlets Filters

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

### TestFilter.java

#### under com.spring.web.filter package

```java
package com.spring.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/TestFilter")
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Print Http Request
		System.out.println(((HttpServletRequest)request).getRequestURI());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
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

 <!-- Wiring TestFilter -->
 <filter>
 <display-name>TestFilter</display-name>
 <filter-name>TestFilter</filter-name>
 <filter-class>com.spring.web.filter.TestFilter</filter-class>
 </filter> 
 
 <filter-mapping>
 <filter-name>TestFilter</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>


</web-app>
```




# Lacture 91-93
## Objective : Adding Spring Security Filter, login form, static resources



	  	<dependency>
	  		<groupId>org.springframework.security</groupId>
	  		<artifactId>spring-security-core</artifactId>
	  		<version>3.2.5.RELEASE</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.springframework.security</groupId>
	  		<artifactId>spring-security-web</artifactId>
	  		<version>3.2.5.RELEASE</version>
	  	</dependency>
	  	<dependency>
	  		<groupId>org.springframework.security</groupId>
	  		<artifactId>spring-security-config</artifactId>
	  		<version>3.2.5.RELEASE</version>
	  	</dependency>

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

### TestFilter.java

#### under com.spring.web.filter package

```java
package com.spring.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/TestFilter")
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Print Http Request
		System.out.println(((HttpServletRequest)request).getRequestURI());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```



# Lacture 94-95
## Objective : Customizing the Login Form and Displaying Login Errors

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

### LoginController.java

```java
package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
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

### TestFilter.java

#### under com.spring.web.filter package

```java
package com.spring.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/TestFilter")
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Print Http Request
		System.out.println(((HttpServletRequest)request).getRequestURI());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
	</div>
</body>
</html>
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />
		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```



# Lacture 96
## Objective : Authorizing Users from Database

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

### LoginController.java

```java
package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
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

### TestFilter.java

#### under com.spring.web.filter package

```java
package com.spring.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/TestFilter")
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Print Http Request
		System.out.println(((HttpServletRequest)request).getRequestURI());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
	</div>
</body>
</html>
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />
		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```




# Lacture 97-98
## Objective : Adding a Create Account Form & Making the Create Account Form work

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "createaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		usersService.create(user);
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

public class User {
	private String username;
	private String password;
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
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



### UsersService.java

```java
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
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
					<label class="col-md-4 control-label" for="name">Username :
					</label>
					<div class="col-md-4">
						<!-- Path must be equal to input name -->
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
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
					<label class="col-md-4 control-label" for="email">Password
						:</label>
					<div class="col-md-4">
						<sf:input id="password" name="password" path="password"
							type="text" class="form-control input-md" />
					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="text"
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
</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```



# Lacture 99
## Objective : Adding Validation to User Form

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "createaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		usersService.create(user);
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
	//user form validation added
	@NotBlank(message="username cannot be blank")
	@Size(min=4  ,max=15, message="User Name must be between 4 to 15 long")
	@Pattern(regexp="^\\w{4,}$",message="Username only consist number letter underscore")
	private String username;
	@NotBlank(message="Password cannot be blank.....")
	@Size(min=8  ,max=15, message="Password must be between 8 to 15 long")
	@Pattern(regexp="^\\S+$",message="Password cannot contain any space")	
	private String password;
	
	@ValidEmail(message="this doesnot seems valid email")
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
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



### UsersService.java

```java
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
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
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
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
							type="text" class="form-control input-md" />
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="confirmpassword">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="text"
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
</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```


# Lacture 100
## Objective : Dealing with Duplicate Usernames

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

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
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist, please choose different username");
			return "newaccount";
		}
		
//		try {
//			usersService.create(user);
//		}catch(DuplicateKeyException e) {
//			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
//			return "newaccount";
//		}
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
	//user form validation added
	@NotBlank(message="username cannot be blank")
	@Size(min=4  ,max=15, message="User Name must be between 4 to 15 long")
	@Pattern(regexp="^\\w{4,}$",message="Username only consist number letter underscore")
	private String username;
	@NotBlank(message="Password cannot be blank.....")
	@Size(min=8  ,max=15, message="Password must be between 8 to 15 long")
	@Pattern(regexp="^\\S+$",message="Password cannot contain any space")	
	private String password;
	
	@ValidEmail(message="this doesnot seems valid email")
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
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
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
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
							type="text" class="form-control input-md" />
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="confirmpassword">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="text"
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
</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```


# Lacture 101
## Objective : Storing Validation Messages in a Property File

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

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
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist, please choose different username");
			return "newaccount";
		}
		
//		try {
//			usersService.create(user);
//		}catch(DuplicateKeyException e) {
//			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
//			return "newaccount";
//		}
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
	//user form validation added
	@NotBlank(message="username cannot be blank")
	@Size(min=4  ,max=15)
	@Pattern(regexp="^\\w{4,}$",message="Username only consist number letter underscore")
	private String username;
	@NotBlank(message="Password cannot be blank.....")
	@Size(min=8  ,max=15, message="Password must be between 8 to 15 long")
	@Pattern(regexp="^\\S+$",message="Password cannot contain any space")	
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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
	ValidEmail.user.email= this does not seems valid email
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
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
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
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
							type="text" class="form-control input-md" />
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="confirmpassword">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="text"
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
</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```

# Lacture 102
## Objective : Using HTML5 Validation to check password fields

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

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
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist, please choose different username");
			return "newaccount";
		}
		
//		try {
//			usersService.create(user);
//		}catch(DuplicateKeyException e) {
//			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
//			return "newaccount";
//		}
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
	//user form validation added
	@NotBlank(message="username cannot be blank")
	@Size(min=4  ,max=15)
	@Pattern(regexp="^\\w{4,}$",message="Username only consist number letter underscore")
	private String username;
	@NotBlank(message="Password cannot be blank.....")
	@Size(min=8  ,max=15, message="Password must be between 8 to 15 long")
	@Pattern(regexp="^\\S+$",message="Password cannot contain any space")	
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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
	ValidEmail.user.email= this does not seems valid email
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
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
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
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
							type="text" class="form-control input-md" />
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="confirmpassword">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="text"
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
	<!--password pamming-->
<script>
    var password1 = document.getElementById('password');
    var password2 = document.getElementById('confirmpassword');

    var checkPasswordValidity = function() {
        if (password1.value != password2.value) {
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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
</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```


# Lacture 103
## Objective : Using Property File Values in JSPs

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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

<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />
	</security:http>
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


</web-app>
```


# Lacture 104
## Objective : Adding a Logout Link

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
```

### loggedout.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>You have logged out........
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />
		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/loggedout" access="permitAll" />		

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />

		<security:logout logout-success-url="/loggedout" />		

	</security:http>
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


</web-app>
```



# Lacture 105
## Objective : Working with Roles 

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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

<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>

<a href="<c:url value='/admin'/>">Admin Page</a>


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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
```

### loggedout.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>You have logged out........
</body>
</html>
```


### admin.jsp

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
if you are admin then you only access this page
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />

		<!-- admin role -->
		<security:intercept-url pattern="/admin" access="hasRole('admin')" />

		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/loggedout" access="permitAll" />		

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />

		<security:logout logout-success-url="/loggedout" />		

	</security:http>
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


</web-app>
```



# Lacture 106
## Objective : Outputting text based on authentication status

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


## Dependencies

	  	<dependency>
	  		<groupId>org.springframework.security</groupId>
	  		<artifactId>spring-security-taglibs</artifactId>
	  		<version>3.2.5.RELEASE</version>
	  	</dependency>

# package name com.spring.web.controller

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

	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
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



### UsersService.java

```java
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
	public boolean exists(String username) {
		return userDao.exists(username);
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

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<!--role based authontication-->
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>

<sec:authorize access="hasRole('admin')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>


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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
```

### loggedout.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>You have logged out........
</body>
</html>
```


### admin.jsp

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
if you are admin then you only access this page
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />

		<!-- admin role -->
		<security:intercept-url pattern="/admin" access="hasRole('admin')" />

		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/loggedout" access="permitAll" />		

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />

		<security:logout logout-success-url="/loggedout" />		

	</security:http>
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


</web-app>
```


# Lacture 107
## Objective : Rowmapping with Bean Property Row Mapper

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
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



### UsersService.java

```java
package com.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<!--role based authontication-->
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>

<sec:authorize access="hasRole('admin')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>


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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
```

### loggedout.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>You have logged out........
</body>
</html>
```


### admin.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
	<table class="table table-striped">
		<tr>
			<th scope="col">Username </th>
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
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />

		<!-- admin role -->
		<security:intercept-url pattern="/admin" access="hasRole('admin')" />

		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/loggedout" access="permitAll" />		

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />

		<security:logout logout-success-url="/loggedout" />		

	</security:http>
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


</web-app>
```


# Lacture 108
## Objective : Using Custom Authentication Queries   Case Sensitive Usernames

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
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



### UsersService.java

```java
package com.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<!--role based authontication-->
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>

<sec:authorize access="hasRole('admin')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>


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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
```

### loggedout.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>You have logged out........
</body>
</html>
```


### admin.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
	<table class="table table-striped">
		<tr>
			<th scope="col">Username </th>
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
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource"
				authorities-by-username-query="SELECT * FROM authorities where binary username = ?"
				users-by-username-query="SELECT * FROM users where binary username = ?" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />

		<!-- admin role -->
		<security:intercept-url pattern="/admin" access="hasRole('admin')" />

		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/loggedout" access="permitAll" />		

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />

		<security:logout logout-success-url="/loggedout" />		

	</security:http>
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


</web-app>
```


# Lacture 109
## Objective : Method Level Access Control

1. context.xml (Tomcat server)


		<Resource name="jdbc/spring" auth="Container" type="javax.sql.DataSource"
		maxActive="100" maxIdle="30" maxWait="10000"
		username="root" password="rootcm" driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/springtutorial?useSSL=false"/>


# package name com.spring.web.controller

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

### LoginController.java

```java
package com.spring.web.controller;

import javax.validation.Valid;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
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

}
```

### UserDao.java

```java
package com.spring.web.dao;

import javax.sql.DataSource;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
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

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<!--role based authontication-->
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>


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

### login.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Added C namespace-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
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
						<input id="j_username" name="j_username" type="text"
							placeholder="" class="form-control input-md">
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
				<!--Username password incorrect alart-->
				</div>
				<div class="alert-danger">
					<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
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
		<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>		
	</div>
</body>
</html>
```



### newaccount.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<sf:form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<fieldset>
<!-- Form Name -->
<legend>Create User</legend>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username : </label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name -->
  <sf:input id="username" path="username" name="username"  type="text" placeholder="Enter your username" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- email input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <sf:input id="email" name="email" path="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>    
  </div>
</div>


<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password :</label>  
  <div class="col-md-4">
  <sf:input id="password" name="password" path="password" type="text" class="form-control input-md"/>
  <sf:errors path="password" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!--Conform Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="confirmpassword">Confirm Password :</label>  
  <div class="col-md-4">
  <input id="confirmpassword" name="confirmpassword"  type="text" class="form-control input-md"/>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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


</body>
</html>
```

### accountcreated.jsp

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
Your account has been created.......
</body>
</html>
```

### loggedout.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>You have logged out........
</body>
</html>
```


### admin.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
	<table class="table table-striped">
		<tr>
			<th scope="col">Username </th>
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
</body>
</html>
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

	<!--linking with message properties....-->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
	<property name="basename" value="com.spring.web.message.message">
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
			<security:user-service>
				<security:user name="abdullah" authorities="admin"
					password="123" />
				<security:user name="abida" authorities="admin"
					password="123" />
			</security:user-service>
		</security:authentication-provider>

		<!--added new authontication provider where reference datasource = dataSource -->
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="dataSource"
				authorities-by-username-query="SELECT * FROM authorities where binary username = ?"
				users-by-username-query="SELECT * FROM users where binary username = ?" />
		</security:authentication-provider>

	</security:authentication-manager>
	
	<security:http use-expressions="true">
		<security:form-login />
		<!--change default spring login path-->
		<security:form-login login-page="/login"
			authentication-failure-url="/login?error=true" />

		<!-- admin role -->
		<!-- <security:intercept-url pattern="/admin" access="hasRole('ROLE_ADMIN')" />-->
		<security:intercept-url pattern="/admin" access="permitAll" />

		<security:intercept-url pattern="/" access="permitAll" />
		<security:intercept-url pattern="/notices" access="permitAll" />
		<!--Permit all static resources like bootstrup library and so one...-->
		<security:intercept-url pattern="/static/**" access="permitAll" />
		<!--Give login url permission-->
		<security:intercept-url pattern="/login" access="permitAll" />

		<security:intercept-url pattern="/loggedout" access="permitAll" />		

		<security:intercept-url pattern="/newaccount" access="permitAll" />
		<security:intercept-url pattern="/createaccount" access="permitAll" />
		<security:intercept-url pattern="/accountcreated" access="permitAll" />

		<security:intercept-url pattern="/createnotice" access="isAuthenticated()" />
		<security:intercept-url pattern="/docreate" access="isAuthenticated()" />
		<security:intercept-url pattern="/**" access="denyAll" />

		<security:logout logout-success-url="/loggedout" />		

	</security:http>
<!--Method level security enabled-->
	<security:global-method-security secured-annotations="enabled"></security:global-method-security>

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


</web-app>
```

