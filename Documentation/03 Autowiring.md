
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