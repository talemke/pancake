import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.security.MessageDigest

@ExperimentalUnsignedTypes
open class PancakeBuildTask : DefaultTask() {

	init {
		// Task information
		description = "Builds and assembles the Pancake application."
		group = "build"

		// Register tasks
		// project.tasks.register("inferLicense", PancakeInferLicenseTask::class.java)
		// project.tasks.register("inferVersion", PancakeInferVersionTask::class.java)

		// Hook tasks
		project.tasks.getByPath("processResources").run {
			finalizedBy("inferLicense")
			finalizedBy("inferVersion")
		}

		// Build the shadowed .jar first
		dependsOn("shadowJar")
	}

	@Input
	var targetDir = File(project.rootDir, "target")

	@Input
	var targetFile = File(targetDir, "Pancake-" + project.version + ".jar")

	@Input
	var shadowFile = File(project.projectDir, "build/libs/" + project.name + "-" + project.version + "-all.jar")



	@TaskAction
	fun pancakeBuild() {
		// Create target dir
		targetDir.deleteRecursively()
		targetDir.mkdirs()

		// Create misc dirs
		File(targetDir, "data").mkdirs()
		File(targetDir, "data/security").mkdirs()
		File(targetDir, "logs").mkdirs()
		File(targetDir, "plugins").mkdirs()

		// Copy shadow file
		require(shadowFile.exists())
		Files.copy(shadowFile.toPath(), targetFile.toPath())

		// Create checksums
		createChecksum("MD5", "CHECKSUM.md5")
		createChecksum("SHA-1", "CHECKSUM.sha1")
	}



	private fun createChecksum(digest: String, fileName: String) {
		val data = Files.readAllBytes(targetFile.toPath())
		val file = File(targetDir, "data/security/$fileName")
		val hashRaw = MessageDigest.getInstance(digest).digest()
		val hash = hashRaw.asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }
		Files.writeString(file.toPath(), hash, Charsets.US_ASCII)
	}

}
