package net.tassia.pancake.config

import java.io.File

/**
 * Used to load and save configs. The configs are analyzed using Reflection and the [ConfigEntry] annotation.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object ConfigIO {

	/**
	 * Saves the given config to a file.
	 *
	 * @param file the file to save to
	 * @param config the config to save
	 */
	fun save(file: File, config: Any) {
		TODO()
	}



	/**
	 * Loads the configuration from the given file.
	 *
	 * @param file the file to load from
	 * @param config the config to load
	 * @return the config
	 */
	fun <T> load(file: File, config: T): T {
		TODO()
	}

}
