#!/bin/bash

mvn clean -Dspring.profiles.active="dev" -P dev tomcat7:run
