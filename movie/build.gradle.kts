plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.0"
}

group = "de.matthiassattel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}