package task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.security.MessageDigest

open class CreateLibChecksumsTask : DefaultTask() {

	init {
		group = "pancake"
		description = "Generates an MD5 and SHA-1 checksum for all libraries."
	}

	@Input
	var libsFolder: File? = null

	@Input
	var checksumsFolder: File? = null

	@TaskAction
	fun exec() {
		val lib = libsFolder.let { require(it != null); return@let it }
		val checksum = checksumsFolder.let { require(it != null); return@let it }

		for (file in lib.listFiles()!!) {
			require(file.name.endsWith(".jar"))
			val data = Files.readAllBytes(file.toPath())
			hash(data, "MD5", File(checksum, file.name.substring(0, file.name.length - 4) + ".md5"))
			hash(data, "SHA-1", File(checksum, file.name.substring(0, file.name.length - 4) + ".sha1"))
		}
	}

	private fun hash(data: ByteArray, algorithm: String, file: File) {
		val hash = MessageDigest.getInstance(algorithm).digest(data)
		val hex = hash.asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }
		Files.writeString(file.toPath(), hex, Charsets.US_ASCII)
	}

}
