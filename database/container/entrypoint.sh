#!/bin/bash

psql -h localhost -U postgres \
 -v database_username=$DATABASE_USERNAME \
 -v database_password=$DATABASE_PASSWORD \
 -v migration_username=$MIGRATION_USERNAME \
 -v migration_password=$MIGRATION_PASSWORD \
 -f /usr/config/init.sql