

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