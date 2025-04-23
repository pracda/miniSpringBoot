# 🌀 Mini Spring Boot Framework

A lightweight Java-based framework inspired by Spring Boot, built to demonstrate key concepts of backend framework development such as Dependency Injection, IoC, custom annotations, reflection, and configuration management.

**🎯 Developed as part of the Advanced Software Development course at Maharishi International University.**

# 🛠️ Core Features
This project was implemented incrementally in multiple parts, each mimicking a feature of the Spring Framework:

✅ **Part A - Service Registration & Basic DI**
Instantiate classes annotated with @Service\
Inject dependencies into fields marked with @Autowired

✅ **Part B - Enhanced DI Mechanisms**
Field injection by name with @Qualifier\
Setter injection using @Autowired on setters\
Constructor injection using @Autowired on constructors

✅ **Part C - Value Injection**
Use @Value annotation to inject values from an application.properties file

✅ **Part D - Spring Boot-style Application Class**
Entry point class implements Runnable\
Bootstrapping using FWApplication.run(...)

✅ **Part E - Profile Support**
Enable environment-based configuration via profiles

✅ **Part F–G - Task Scheduling**
Basic task scheduling with @Scheduled\
Custom cron-like expressions (e.g., @Scheduled(cron = "5 0"))

✅ **Part H - Event Handling**
Support for publish-subscribe patterns using events

✅ **Part I - Configuration Properties**
Read grouped properties using @ConfigurationProperties

✅ **Part J - Asynchronous Processing**
Support for asynchronous execution using @Async

⭐ **Part K (Extra Credit) - AOP Support (Before/After)**
Simple AOP using @Before and @After annotations

⭐ **Part L (Extra Credit) - Around Advice**
Support for @Around advice in AOP

# 🧰 Technologies Used
Java 17\
Maven\
Reflection API\
Custom Annotations\
Design Patterns (Factory, Singleton, Proxy, etc.)\
IntelliJ IDEA

# ▶️ Getting Started
**Prerequisites**\
Java JDK 17+\
Maven installed\
IDE like IntelliJ IDEA

**How to Run**
**Clone the repository:**
git clone https://github.com/pracda/miniSpringBoot.git\

**Navigate into the project and build:**
cd mini-spring-boot-framework
mvn clean install

**Open in IntelliJ and run:**
src/app/AnnotationApp.java or Application.java depending on the feature you're testing.\

# 💡 What I Learned
**🔍 Deep dive into Java Reflection and runtime annotation processing**\
**🧠 Understanding how IoC containers are built and initialized**\
**🧱 Creating extensible architecture patterns similar to enterprise frameworks**\
**🔁 Mimicking Spring Boot's startup flow, annotations, and modularity**

# 🙌 Acknowledgements
This project was inspired by the Spring Framework and enriched through coursework discussions at Maharishi International University.

# 📬 Contact
Want to collaborate or ask a question?\
📧 Email: prasiddhapaudel9@gmail.com\
🔗 LinkedIn: https://www.linkedin.com/in/prasiddha-p-a5a757aa/
