group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

plugins {
	kotlin("jvm") version PancakeBuild.Version.Kotlin
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":libraries:utils"))
}

kotlin {
	sourceSets {
		val main by getting
		val test by getting
	}
}
