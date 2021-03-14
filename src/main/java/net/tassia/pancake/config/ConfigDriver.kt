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
	 *
	 * @param reader the reader to read from
	 * @return the read config
	 */
	abstract fun read(reader: Reader): Map<String, String>

	/**
	 * Writes a configuration.
	 *
	 * @param writer the writer to read to
	 * @param map the config
	 * @param commenter the commenter
	 */
	abstract fun write(writer: Writer, map: Map<String, String>, commenter: ConfigCommenter)

}
