import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class PancakePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		// Initialize
		val targetDir = File(target.rootDir, "target")

		// Register tasks
		target.tasks.register("pancake-build", DefaultTask::class.java) {
			it.group = "pancake"
			it.description = "Builds the Pancake application."
			it.dependsOn("distZip")
		}
		target.tasks.register("pancake-createLibChecksums", task.CreateLibChecksumsTask::class.java) {
			it.libsFolder = File(targetDir, "lib")
			it.checksumsFolder = File(targetDir, "data/security/checksum")
		}
		target.tasks.register("pancake-inferLicense", task.InferLicenseTask::class.java)
		target.tasks.register("pancake-inferVersion", task.InferVersionTask::class.java)
		target.tasks.register("pancake-prepareTargetFolder", task.PrepareTargetFolderTask::class.java) {
			it.targetDir = targetDir
		}
		target.tasks.register("pancake-unzipDist", task.UnzipDistributionTask::class.java) {
			it.dependsOn("pancake-prepareTargetFolder")
			it.finalizedBy("pancake-createLibChecksums")
		}

		// Hook tasks
		target.tasks.getByName("processResources").finalizedBy("pancake-inferLicense", "pancake-inferVersion")
		target.tasks.getByName("distZip").finalizedBy("pancake-unzipDist")
	}

}
