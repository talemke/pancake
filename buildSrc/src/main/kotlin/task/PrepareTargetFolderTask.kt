package task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

open class PrepareTargetFolderTask : DefaultTask() {

	init {
		group = "pancake"
		description = "Prepares the target folder."
	}

	@Input
	var targetDir: File? = null

	@TaskAction
	fun exec() {
		targetDir.let {
			require(it != null)

			// Create target dir
			it.deleteRecursively()
			it.mkdirs()

			// Create misc dirs
			File(it, "data").mkdirs()
			File(it, "data/security").mkdirs()
			File(it, "data/security/checksum").mkdirs()
			File(it, "logs").mkdirs()
			File(it, "plugins").mkdirs()
		}
	}

}
