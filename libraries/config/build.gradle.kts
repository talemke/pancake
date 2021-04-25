group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

plugins {
	kotlin("multiplatform") version PancakeBuild.Version.Kotlin
}

repositories {
	mavenCentral()
}

kotlin {
	jvm()
	js {
		browser()
		nodejs()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(kotlin("stdlib-common"))
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}
		val jvmTest by getting {
			dependencies {
				implementation(kotlin("test-junit"))
			}
		}
		val jsTest by getting {
			dependencies {
				implementation(kotlin("test-js"))
			}
		}
	}
}
