import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class PancakePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		// Initialize
		val targetDir = File(target.rootDir, "target")

		// Register tasks
		target.tasks.register("pancake-prepareTargetFolder", task.PrepareTargetFolderTask::class.java) {
			it.targetDir = targetDir
		}
		target.tasks.register("pancake-inferLicense", task.InferLicenseTask::class.java)
		target.tasks.register("pancake-inferVersion", task.InferVersionTask::class.java)
		target.tasks.register("pancake-inferVersion", task.UnzipDistributionTask::class.java)
		// TODO: Create checksums
	}

}
