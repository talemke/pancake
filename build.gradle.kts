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

	api("org.jetbrains.exposed", "exposed-core", exposedVersion)
	api("org.jetbrains.exposed", "exposed-dao", exposedVersion)
	api("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)

	api("io.ktor", "ktor-server-core", ktorVersion)
	api("io.ktor", "ktor-server-netty", ktorVersion)
	api("io.ktor", "ktor-server-host-common", ktorVersion)

	api("io.ktor", "ktor-client-core", ktorVersion)
	api("io.ktor", "ktor-client-core-jvm", ktorVersion)
	api("io.ktor", "ktor-client-apache", ktorVersion)

	api("io.ktor", "ktor-auth", ktorVersion)
	api("io.ktor", "ktor-jackson", ktorVersion)
	api("io.ktor", "ktor-metrics", ktorVersion)

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

tasks.named("processResources") {
	finalizedBy("injectVersionInformation")
}

tasks.create<Copy>("injectVersionInformation") {
	from("src/main/resources/net/tassia/pancake/resources/internal/version.properties")
	into("$buildDir/resources/main/net/tassia/pancake/resources/internal/")
	filter { line -> line
		.replace("%{version.major}", 0.toString())
		.replace("%{version.minor}", 0.toString())
		.replace("%{version.patch}", 1.toString())
		.replace("%{version.build}", 1.toString())

		.replace("%{version.timestamp}", System.currentTimeMillis().toString())
		.replace("%{version.extension}", "SNAPSHOT")
		.replace("%{version.snapshot}", true.toString())

		.replace("%{version.git.head}", "d96f6609fb66c5550ed7b9027e565b5b40627a8c")
		.replace("%{version.git.branch}", "main")
	}
}
