group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

apply<PancakePlugin>()

plugins {
	kotlin("jvm") version PancakeBuild.Version.Kotlin
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
	mainClass.set("net.tassia.pancake.bootstrap.EntrypointKt")
}
