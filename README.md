### Introduction

exhubs is an exam application which is powered by Spring MVC 3.2, Hibernate, JPA, spring-data. 


### Installation

* `git clone https://github.com/bml3i/exhubs.git`

* `cd exhubs`

* `mvn eclipse:eclipse`

* `mvn eclipse:clean`


### Run

DEV:

* `mvn clean -P dev install`

* `java -Dspring.profiles.active=dev -jar target/dependency/jetty-runner.jar target/*.war`  

OR

* `mvn clean -Dspring.profiles.active="dev" -P dev tomcat7:run`  


UAT:

* Set TimeZone: `heroku config:add TZ=Asia/Shanghai`
* Set Procfile: `java $JAVA_OPTS -Dspring.profiles.active=uat -jar target/dependency/jetty-runner.jar --port $PORT target/*.war`
