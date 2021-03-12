package net.tassia.pancake.config

import java.io.Reader
import java.io.Writer
import java.util.*

/**
 * Defines how configs should be read and stored.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class ConfigDriver(

	/**
	 * The common file suffix for the config type.
	 */
	val fileSuffix: String,

	/**
	 * The read function.
	 */
	val read: (Reader) -> Map<String, String>,

	/**
	 * The write function.
	 */
	val write: (Map<String, String>, Writer) -> Unit,

) {

	/**
	 * The `.ini` file format.
	 */
	INI("ini", fun(reader): Map<String, String> {
		// Create and read properties
		val props = Properties()
		props.load(reader)

		// Transform to map
		val map = mutableMapOf<String, String>()
		for (name in props.stringPropertyNames()) {
			map[name] = props[name]?.toString() ?: continue
		}

		// Return map
		return map
	}, fun(map, writer) {
		// Create empty properties
		val props = Properties()

		// Transform to properties
		for (entry in map.entries) {
			props[entry.key] = entry.value
		}

		// Write properties
		props.store(writer, null)
	});

}
