plugins {
    id("org.springframework.boot") version "2.7.4"
}

apply(plugin = "org.springframework.boot")

dependencies {
    implementation(project(":isla-del-lago-common"))
    implementation(project(":isla-del-lago-core"))
    implementation(project(":isla-del-lago-domain"))
    implementation(project(":isla-del-lago-schema"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
