plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.trackapi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// ✅ Forçar versão compatível do spring-web para evitar conflitos
configurations.all {
	resolutionStrategy {
		eachDependency {
			if (requested.group == "org.springframework" && requested.name.startsWith("spring-web")) {
				useVersion("6.1.4")
			}
		}
	}
}

tasks.jar{
	manifest{
		attributes["Main-Class"] = "com.trackapi.Application"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
