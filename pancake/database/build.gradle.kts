group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

plugins {
	kotlin("jvm") version PancakeBuild.Version.Kotlin
}

repositories {
	mavenCentral()
}

dependencies {
	api("org.jetbrains.exposed", "exposed-core", PancakeBuild.Version.Exposed)
	api("org.jetbrains.exposed", "exposed-dao", PancakeBuild.Version.Exposed)
	api("org.jetbrains.exposed", "exposed-jdbc", PancakeBuild.Version.Exposed)
	implementation("org.xerial", "sqlite-jdbc", "3.34.0")
	implementation(project(":pancake:core"))
}

kotlin {
	sourceSets {
		val main by getting
		val test by getting
	}
}
