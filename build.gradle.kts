import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("jvm") version "1.6.21"
}

allprojects {

	apply(plugin = "kotlin")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin-spring")
	apply(plugin = "kotlin-allopen")

	java.sourceCompatibility = JavaVersion.VERSION_11

	group = "isla.del.lago.shenglong"

	repositories {
		mavenCentral()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
