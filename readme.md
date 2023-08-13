# Ecommerce messaging

The goal of this project is to deep dive into some concepts about kafka and messaging architecture. It aims to emulate
how an ecommerce would behave on an event driven environment. Not every feature is mapped, this project is intended to be
incremented from time to time with new functionalities.

## How to run locally (WIP)

In order to run this app locally we need to set up some components, most of them being docker containers namely:

- Database (Postgres)
  
To initialize the container:

```shell
  ./gradlew dbUp
```

To shut down the container:
```shell
./gradlew dbDown
```

- Flyway

we use flyway to manage the database versioning. There are gradle tasks to run the migrations (WIP)

- Kafka (WIP)
