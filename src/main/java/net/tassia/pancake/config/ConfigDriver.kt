package net.tassia.pancake.config

import java.io.Reader
import java.io.Writer

/**
 * Defines how configs should be read and stored.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
abstract class ConfigDriver(

	/**
	 * The common file suffix for the config type.
	 */
	val fileSuffix: String

) {

	/**
	 * Reads a configuration.
	 */
	abstract fun read(reader: Reader): Map<String, String>

	/**
	 * Writes a configuration.
	 */
	abstract fun write(writer: Writer, map: Map<String, String>)

}
