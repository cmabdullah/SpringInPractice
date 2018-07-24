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