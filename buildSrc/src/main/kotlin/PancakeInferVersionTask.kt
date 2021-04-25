import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files

open class PancakeInferVersionTask : DefaultTask() {

	@Input
	var targetFile = File(project.buildDir, "resources/main/net/tassia/pancake/VERSION")



	@TaskAction
	fun inferLicense() {
		targetFile.parentFile.mkdirs()
		Files.writeString(targetFile.toPath(), project.version.toString(), Charsets.US_ASCII)
	}

}
