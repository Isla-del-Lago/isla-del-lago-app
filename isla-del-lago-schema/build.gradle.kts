import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.7.4"
}

dependencies {
    implementation("org.flywaydb:flyway-core:9.3.1")
    implementation("org.flywaydb:flyway-mysql")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
