package net.tassia.pancake.logging.publisher

import net.tassia.pancake.logging.formatter.LogFormatter
import java.io.File
import java.io.FileOutputStream
import java.io.PrintStream

/**
 * Assures the given file exists.
 *
 * @param file the file
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
private fun assureExists(file: File) {
	if (file.exists()) return
	file.parentFile.mkdirs()
	file.createNewFile()
}

/**
 * Creates a new [PrintStream] for the given file.
 * This also ensures the file exists.
 *
 * @param file the file
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
private fun createStream(file: File): PrintStream {
	assureExists(file)
	return PrintStream(FileOutputStream(file))
}



/**
 * This publishers writes logs to a given file.
 *
 * @param file the file to write to
 * @param formatter the formatter to use
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class FilePublisher<DATA : Any>(

	file: File,
	override val formatter: LogFormatter<DATA>,

) : PrintStreamPublisher<DATA>(createStream(file), formatter)
