#!/bin/bash

cat ./src/main/webapp/WEB-INF/sqls/postgresql_init.sql | heroku pg:psql
