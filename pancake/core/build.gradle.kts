group = PancakeBuild.GROUP
version = PancakeBuild.VERSION

plugins {
	kotlin("multiplatform") version "1.4.32"
}

repositories {
	mavenCentral()
}

dependencies {
	commonMainApi(project(":libraries:config"))
	commonMainApi(project(":libraries:event"))
	commonMainApi(project(":libraries:logging"))
	commonMainApi(project(":libraries:utils"))
}

kotlin {
	jvm()
	js {
		browser {
			testTask {
				useMocha {
					timeout = "1m"
				}
			}
		}
		nodejs {
			testTask {
				useMocha {
					timeout = "1m"
				}
			}
		}
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
	}
}
