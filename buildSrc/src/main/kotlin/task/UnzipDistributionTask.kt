package task

import org.gradle.api.file.RelativePath
import org.gradle.api.tasks.Copy
import java.io.File

open class UnzipDistributionTask : Copy() {

	init {
		// Task information
		description = "Unzips the distribution archive and copies it into the target folder."
		group = "pancake"

		// Unzip
		val projectFile = project.run { "$buildDir/distributions/$name-$version.zip" }
		from(project.zipTree(projectFile))
		into(File(project.rootDir, "target"))
		eachFile {
			if (it.isDirectory) {
				it.exclude()
			} else {
				it.path = it.path.replace(project.name + "-" + project.version + "/", "")
			}
		}
	}

}
