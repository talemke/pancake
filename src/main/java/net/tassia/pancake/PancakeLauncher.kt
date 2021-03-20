package net.tassia.pancake

import net.tassia.pancake.util.config.ConfigIO
import net.tassia.pancake.util.config.driver.ConfigIniDriver
import net.tassia.pancake.util.DatabaseConnector
import net.tassia.pancake.config.PancakeConfig
import net.tassia.pancake.logging.Logger
import net.tassia.pancake.logging.formatter.DefaultFormatter
import net.tassia.pancake.logging.publisher.PrintStreamPublisher
import net.tassia.pancake.util.PancakeIO
import java.io.File

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

		// Prepare logger
		Logger.publishers.add(PrintStreamPublisher(
			stream = System.out,
			formatter = DefaultFormatter,
		))

		// Connect to database
		DatabaseConnector.connect(cfg)

		// Launch
		return Pancake(cfg)
	}

}
