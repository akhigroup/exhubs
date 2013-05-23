### Introduction

exhubs is an exam application which is powered by Spring MVC 3.2, Hibernate, JPA, spring-data. 
 
### Installation

git clone https://github.com/bml3i/exhubs.git

cd exhubs

mvn eclipse:eclipse

mvn eclipse:clean

`DEV:` mvn clean -Dspring.profiles.active="dev" tomcat7:run

`PROD:` mvn clean tomcat7:run
