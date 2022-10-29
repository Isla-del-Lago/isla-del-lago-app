import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

allprojects {

	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "kotlin-allopen")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	group = "isla.del.lago.shenglong"
	java.sourceCompatibility = JavaVersion.VERSION_11

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("org.springframework.boot:spring-boot-starter-validation")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.flywaydb:flyway-core:9.3.1")
		implementation("org.flywaydb:flyway-mysql")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		runtimeOnly("mysql:mysql-connector-java")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		compileOnly("io.jsonwebtoken:jjwt-api:0.11.5")
		runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
		implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
		mainClass.set("isla.del.lago.shenglong.ApplicationKt")
		archiveClassifier.set("boot")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
