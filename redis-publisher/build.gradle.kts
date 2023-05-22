plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.0"
    application
}

group = "de.matthiassattel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("de.matthiassattel.RedisPublisherApplication")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":movie"))
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.data:spring-data-redis")
    implementation("io.lettuce:lettuce-core:6.2.4.RELEASE")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
}

tasks.test {
    useJUnitPlatform()
}