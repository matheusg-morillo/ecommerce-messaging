plugins {
    java
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("org.flywaydb.flyway") version "9.21.1"
    id("co.uzzu.dotenv.gradle") version "2.0.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.2")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.flywaydb:flyway-core:9.21.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

flyway {
    val databaseUrl = env.DATABASE_URL.value
    val migrationUsername = env.MIGRATION_USERNAME.value
    val migrationPassword = env.MIGRATION_PASSWORD.value
    val databaseSchema = "ECOMMERCE_MESSAGING"

    url = databaseUrl
    user = migrationUsername
    password = migrationPassword
    schemas = arrayOf(databaseSchema)
}

tasks.register("dbUp") {
    exec {
        executable("docker")
        args("compose", "up", "database", "-d")
    }
}

tasks.register("dbDown") {
    exec {
        executable("docker")
        args("compose", "down", "database")
    }
}

tasks.register("dbInit") {
    val databaseUsername = env.DATABASE_USERNAME.value
    val databasePassword = env.DATABASE_PASSWORD.value
    val migrationUsername = env.MIGRATION_USERNAME.value
    val migrationPassword = env.MIGRATION_PASSWORD.value

    exec {
        executable("docker")
        args("compose", "exec", "-it", "database", "psql", "-U", "postgres", "-v", "database_username=$databaseUsername", "-v", "database_password=$databasePassword", "-v", "migration_username=$migrationUsername", "-v", "migration_password=$migrationPassword", "-f", "/usr/config/init.sql")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
