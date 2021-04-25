package net.tassia.pancake.util.config.provider

import net.tassia.pancake.util.config.Configuration

/**
 * A configuration provider defines how a [Configuration] is loaded and/or saved.
 *
 * @since PancakeConfig 1.0
 * @author Tassilo
 */
abstract class ConfigurationProvider {

	/**
	 * Loads the given [Configuration] from the given input string.
	 *
	 * @param input the input string
	 * @param config the configuration
	 */
	abstract fun load(input: String, config: Configuration)

	/**
	 * Saves the given [Configuration].
	 *
	 * @param config the configuration to save
	 * @return the built string
	 */
	abstract fun save(config: Configuration): String

}
