# Ecommerce messaging

The goal of this project is to deep dive into some concepts about kafka and messaging architecture. It aims to emulate
how an ecommerce would behave on an event driven environment. Not every feature is mapped, this project is intended to be
incremented from time to time with new functionalities.

## Concepts and technologies used

- Java 17
- Postgres
- Docker
- Flyway
- Gradle
- DDD and TDD
- Junit
- Kafka

## Requirements

- Java 17+
- Docker/Docker compose

## How to run locally (WIP)

### Dotenv file

Create a .env file copying all the values from the .env-example file. Any values can be used for the DATABASE_USERNAME,
DATABASE_PASSWORD, MIGRATION_USERNAME and MIGRATION_PASSWORD. Only change the DATABASE_URL if you for any reason modified 
the port of the container or the database name.

### Infra setup

In order to run this app locally we need to set up some components, most of them being docker containers namely:

```shell
./gradlew dbUp
```

To set up the database (create users and schema):
```shell
./gradlew dbInit
```

To apply the latest version of the database

```shell
./gradlew flywayMigrate
```

**There's no way to undo migrations since the community edition of flyway does not support this feature. To undo any change
a new version is required.**

## Endpoints