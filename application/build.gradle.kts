plugins {
    java
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("org.flywaydb.flyway") version "9.21.1"
}

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
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.flywaydb:flyway-core:9.21.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

flyway {
    val databaseUrl = env.DATABASE_URL.value
    val migrationUsername = env.MIGRATION_USERNAME.value
    val migrationPassword = env.MIGRATION_PASSWORD.value
    val databaseSchema = "ecommerce_messaging"

    url = databaseUrl
    user = migrationUsername
    password = migrationPassword
    schemas = arrayOf(databaseSchema)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
