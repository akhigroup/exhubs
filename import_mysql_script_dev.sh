#!/bin/bash

mysql -h localhost -u root -pAa1234 < ./src/main/webapp/WEB-INF/sqls/mysql_init.sql
