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
	//@Pointcut("execution( void com.spring.aop.Camera.snap(..))")// this pointcut will execute for all snap methid with different parameter.......
	//@Pointcut("execution( *  com.spring.aop.Camera.snap(..))")// wildcut all snap method type....
	//@Pointcut("execution( *  com.spring.aop.Camera.*(..))")// this execution joinpoint will axecute for all method inside Camera class....
	@Pointcut("execution( *  com.spring.aop.Camera.*(..))")
	//@Pointcut("execution( *  *.*(..))")//this join point will execute for all classes....... methods...
	public void cameraSnap() {
		
	}
	@Pointcut("execution( *  *.*(..))")//this pointcut will execute for all package ,all classes, allmethodes....
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