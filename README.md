

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

