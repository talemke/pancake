group = "net.tassia"
version = "0.0.1"

plugins {
	java; `java-library`; `maven-publish`
	kotlin("jvm") version "1.5.20"
}

repositories {
	mavenCentral()
	maven("https://nexus.tassia.net/repository/maven-public/")
}

dependencies {
	val exposedVersion = "0.30.1"
	val ktorVersion = "1.6.0"

	api("net.tassia", "event-api", "1.0.1")

	implementation("org.postgresql:postgresql:42.2.2")
	implementation("mysql:mysql-connector-java:5.1.48")
	implementation("org.xerial:sqlite-jdbc:3.30.1")
	implementation("com.h2database:h2:1.4.199")

	implementation("org.jetbrains.exposed", "exposed-core", exposedVersion)
	implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
	implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)

	implementation("io.ktor", "ktor-server-core", ktorVersion)
	implementation("io.ktor", "ktor-server-netty", ktorVersion)
	implementation("io.ktor", "ktor-server-host-common", ktorVersion)

	implementation("io.ktor", "ktor-client-core", ktorVersion)
	implementation("io.ktor", "ktor-client-core-jvm", ktorVersion)
	implementation("io.ktor", "ktor-client-apache", ktorVersion)

	implementation("io.ktor", "ktor-auth", ktorVersion)
	implementation("io.ktor", "ktor-jackson", ktorVersion)
	implementation("io.ktor", "ktor-metrics", ktorVersion)

	testImplementation(kotlin("test-junit"))
}

java {
	this.sourceCompatibility = JavaVersion.VERSION_1_8
	this.targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
	sourceSets {
		val main by getting
		val test by getting
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}
	repositories {
		maven("https://nexus.tassia.net/repository/maven-releases/") {
			credentials {
				username = findProperty("PublishMavenUsername") as String
				password = findProperty("PublishMavenPassword") as String
			}
		}
	}
}
