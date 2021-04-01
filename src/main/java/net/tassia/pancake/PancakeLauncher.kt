package net.tassia.pancake

import net.tassia.pancake.util.config.ConfigIO
import net.tassia.pancake.util.config.driver.ConfigIniDriver
import net.tassia.pancake.util.DatabaseConnector
import net.tassia.pancake.config.PancakeConfig
import net.tassia.pancake.logging.Logger
import net.tassia.pancake.logging.formatter.DefaultAnsiFormatter
import net.tassia.pancake.logging.formatter.DefaultFormatter
import net.tassia.pancake.logging.publisher.BinaryStreamPublisher
import net.tassia.pancake.logging.publisher.FilePublisher
import net.tassia.pancake.logging.publisher.PrintStreamPublisher
import net.tassia.pancake.util.PancakeIO
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * This object is used to launch a new [Pancake] instance.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object PancakeLauncher {

	/**
	 * Launches a new [Pancake] instance.
	 *
	 * @param args command-line arguments
	 * @return the instance
	 */
	fun new(args: Array<String>): Pancake {
		// Prepare IO
		PancakeIO.createFolders()

		// Load configuration
		val cfg = File("config.ini").let {
			val config = PancakeConfig()
			if (it.exists()) {
				ConfigIO.load(it, config, ConfigIniDriver)
			} else {
				ConfigIO.save(it, config, ConfigIniDriver)
			}
			return@let config
		}

		// Register log publishers
		addLogPublishers()

		// Connect to database
		DatabaseConnector.connect(cfg)

		// Launch
		return Pancake(cfg)
	}



	/**
	 * Registers all required logging publishers.
	 */
	private fun addLogPublishers() {
		val time = System.currentTimeMillis()

		// Add console output publisher to logger
		Logger.publishers.add(PrintStreamPublisher(
			stream = System.out,
			formatter = DefaultAnsiFormatter,
		))

		// Add file publisher to logger
		Logger.publishers.add(FilePublisher(
			file = getLogFile(time, "txt"),
			formatter = DefaultFormatter,
		))

		// Add binary publisher
		Logger.publishers.add(BinaryStreamPublisher(
			stream = FileOutputStream(getLogFile(time, "bin"))
		))
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
