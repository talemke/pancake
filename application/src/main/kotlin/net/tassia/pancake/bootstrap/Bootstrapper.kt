package net.tassia.pancake.bootstrap

import net.tassia.pancake.Logger
import net.tassia.pancake.Pancake
import net.tassia.pancake.PancakeBootException
import net.tassia.pancake.config.PancakeConfig
import net.tassia.pancake.database.DatabaseConnector
import net.tassia.pancake.logging.formatter.DefaultAnsiFormatter
import net.tassia.pancake.logging.formatter.DefaultFormatter
import net.tassia.pancake.logging.publisher.BinaryStreamPublisher
import net.tassia.pancake.logging.publisher.FilePublisher
import net.tassia.pancake.logging.publisher.PrintStreamPublisher
import net.tassia.pancake.util.PancakeIO
import net.tassia.pancake.util.config.provider.ConfigurationProvider
import net.tassia.pancake.util.Version
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.*

/**
 * The bootstrapper for Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Bootstrapper {

	/**
	 * Launches the Pancake application.
	 *
	 * @param args command-line arguments
	 */
	fun launch(args: Array<String>) {
		// Prepare IO
		PancakeIO.createFolders()

		// Parse version
		val version = Bootstrapper::class.java.getResourceAsStream("/net/tassia/pancake/VERSION")?.use {
			val str = String(it.readAllBytes(), Charsets.UTF_8)
			return@use Version.parse(str) ?: throw PancakeBootException("VERSION file does not contain a valid version.")
		} ?: throw PancakeBootException("VERSION file does not exist in class path.")

		// Load configuration
		val config = File("").let {
			val cfg = PancakeConfig()
			if (it.exists()) {
				val provider: ConfigurationProvider = TODO()
				provider.load(Files.readString(it.toPath(), Charsets.UTF_8), cfg)
			} else {
				throw PancakeBootException("No config file exists.")
			}
			return@let cfg
		}

		// Register log publishers
		addLogPublishers()

		// Connect to database
		DatabaseConnector.connect(config)

		// Launch
		Pancake(config, version)
	}



	/**
	 * Registers all required logging publishers.
	 */
	private fun addLogPublishers() {
		val time = System.currentTimeMillis()

		// Add console output publisher to logger
		Logger.publishers.add(PrintStreamPublisher(System.out, DefaultAnsiFormatter))

		// Add file publisher to logger
		Logger.publishers.add(FilePublisher(getLogFile(time, "txt"), DefaultFormatter))

		// Add binary publisher
		Logger.publishers.add(BinaryStreamPublisher(FileOutputStream(getLogFile(time, "bin"))))
	}



	/**
	 * Generates a new log file for the current time.
	 *
	 * @return the generated log file
	 */
	private fun getLogFile(time: Long, suffix: String): File {
		val sdf = SimpleDateFormat("yyyy-MM-dd-'at'-HH-mm")
		val date = sdf.format(Date(time))
		return File("logs", "$date.$suffix")
	}

}
