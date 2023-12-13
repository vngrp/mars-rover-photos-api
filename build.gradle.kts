import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.asciidoctor.jvm.convert") version "3.3.2"

	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"

	kotlin("plugin.serialization") version "1.9.21"
}

group = "fm.pim"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

val ktorClientVersion = "2.3.7"
val kotestVersion = "5.7.2"
val arrowVersion = "1.2.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-webtestclient")

	implementation("io.arrow-kt:arrow-core:$arrowVersion")
	implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")

	implementation("io.ktor:ktor-client-core:$ktorClientVersion")
	implementation("io.ktor:ktor-client-json:$ktorClientVersion")
	implementation("io.ktor:ktor-client-cio:$ktorClientVersion")
	implementation("io.ktor:ktor-client-content-negotiation:$ktorClientVersion")

	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")

	testImplementation("com.lemonappdev:konsist:0.12.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

