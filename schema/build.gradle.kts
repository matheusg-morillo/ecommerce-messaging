plugins {
    java
    `maven-publish`
    id("io.spring.dependency-management") version "1.1.2"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.8.0"
}

group = "com.matheus"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "ecommerce-messaging-schema"
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}

dependencies {
    implementation("org.springframework:spring-messaging:6.0.6")
    implementation("org.apache.avro:avro:1.11.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    archiveBaseName.set("ecommerce-messaging-schema")
}

tasks.named<Copy>("processResources") {
   from("src/main/avro") {
       include("**/*.avro")

   }
}
