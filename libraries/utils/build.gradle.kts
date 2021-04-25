group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

plugins {
	kotlin("multiplatform") version PancakeBuild.Version.Kotlin
}

repositories {
	mavenCentral()
}

dependencies {
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
		val jvmMain by getting {
			dependencies {
				implementation("com.fasterxml.jackson.core:jackson-core:" + PancakeBuild.Version.Jackson)
				implementation("com.fasterxml.jackson.core:jackson-databind:" + PancakeBuild.Version.Jackson)
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
