#!/bin/bash

psql -h locahost -U postgres -f /docker-entrypoint-initdb.d/init.sql