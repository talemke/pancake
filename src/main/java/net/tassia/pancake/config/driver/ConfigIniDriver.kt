package net.tassia.pancake.config.driver

import net.tassia.pancake.config.ConfigDriver
import java.io.Reader
import java.io.Writer
import java.util.*

/**
 * The `.ini` file format.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object ConfigIniDriver : ConfigDriver("ini") {

	override fun read(reader: Reader): Map<String, String> {
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
	}

	override fun write(writer: Writer, map: Map<String, String>) {
		// Create empty properties
		val props = Properties()

		// Transform to properties
		for (entry in map.entries) {
			props[entry.key] = entry.value
		}

		// Write properties
		props.store(writer, null)
	}

}
