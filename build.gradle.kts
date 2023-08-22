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
    mavenLocal()
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

tasks.register<Exec>("dbUp") {
    executable("docker")
    args("compose", "up", "db", "-d")
}

tasks.register<Exec>("dbDown") {
    executable("docker")
    args("compose", "down", "db")
}

tasks.register<Exec>("dbCreate") {
    val databaseUsername = env.DATABASE_USERNAME.value
    val databasePassword = env.DATABASE_PASSWORD.value
    val migrationUsername = env.MIGRATION_USERNAME.value
    val migrationPassword = env.MIGRATION_PASSWORD.value

    executable("docker")
    args("compose", "exec", "-it", "db", "psql", "-U", "postgres",
            "-v", "database_username=$databaseUsername",
            "-v", "database_password=$databasePassword",
            "-v", "migration_username=$migrationUsername",
            "-v", "migration_password=$migrationPassword",
            "-f", "/usr/config/create_database.sql"
    )

    finalizedBy("dbCreateUsers")
}

tasks.register<Exec>("dbCreateUsers") {
    val databaseUsername = env.DATABASE_USERNAME.value
    val databasePassword = env.DATABASE_PASSWORD.value
    val migrationUsername = env.MIGRATION_USERNAME.value
    val migrationPassword = env.MIGRATION_PASSWORD.value

    executable("docker")
    args("compose", "exec", "-it", "db", "psql", "-U", "postgres", "-d", "ecommerce_messaging",
            "-v", "database_username=$databaseUsername",
            "-v", "database_password=$databasePassword",
            "-v", "migration_username=$migrationUsername",
            "-v", "migration_password=$migrationPassword",
            "-f", "/usr/config/create_users.sql"
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}
