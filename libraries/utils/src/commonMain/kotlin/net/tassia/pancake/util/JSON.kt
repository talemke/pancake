package net.tassia.pancake.util

import kotlin.reflect.KClass

/**
 * Allows to read/write JSON objects.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
expect object JSON {

	/**
	 * Converts the given object to a JSON string.
	 *
	 * @param any the object
	 * @param prettyPrint whether pretty-print should be enabled
	 * @return the JSON string
	 */
	fun stringify(any: Any, prettyPrint: Boolean = false): String

	/**
	 * Parses the given JSON string to an instance of the given source class.
	 *
	 * @param json the JSON string
	 * @param sourceClass the source class
	 * @return the parsed object
	 */
	fun <T : Any> parse(json: String, sourceClass: KClass<T>): T

	/**
	 * Parses the given JSON string to an instance.
	 *
	 * @param json the JSON string
	 * @return the parsed object
	 */
	inline fun <reified T : Any> parse(json: String): T

}
