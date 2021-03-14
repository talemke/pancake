package net.tassia.pancake.config

/**
 * Adds a description to an entire config section.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
annotation class ConfigSection(

	/**
	 * The section name.
	 */
	val section: String,

	/**
	 * The section description.
	 */
	val description: String

)
