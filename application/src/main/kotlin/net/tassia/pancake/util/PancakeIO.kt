package net.tassia.pancake.util

import java.io.File

/**
 * Handles common I/O stuff for Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object PancakeIO {

	/**
	 * Creates all necessary folders for Pancake.
	 */
	fun createFolders() {
		// Create data folder
		File("./data/").mkdirs()

		// Create logs folder
		File("./logs/").mkdirs()

		// Create plugins folder
		File("./plugins/").mkdirs()
	}

}
