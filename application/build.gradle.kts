group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

plugins {
	kotlin("jvm") version PancakeBuild.Version.Kotlin
	// id("com.github.johnrengelman.shadow").version("6.1.0")
	application
}

repositories {
	mavenCentral()
}

dependencies {
	api(project(":libraries:utils-jvm"))
	api(project(":pancake:core"))
	api(project(":pancake:database"))
}

kotlin {
	sourceSets {
		val main by getting
		val test by getting
	}
}

application {
	applicationName = "Pancake"
	mainClass.set("net.tassia.pancake.bootstrap.EntrypointKt")
}

tasks.register("inferLicense", PancakeInferLicenseTask::class.java)
tasks.register("inferVersion", PancakeInferVersionTask::class.java)

tasks["processResources"].run {
	finalizedBy("inferLicense")
	finalizedBy("inferVersion")
}

tasks.register<PancakeBuildTask>("buildPancake")
