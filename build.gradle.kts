plugins {
    java
    id("org.flywaydb.flyway") version "9.21.1"
    id("co.uzzu.dotenv.gradle") version "2.0.0"
}

group = "com.matheus"
version = "0.0.1-SNAPSHOT"

tasks.withType<Test> {
    useJUnitPlatform()
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