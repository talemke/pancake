package net.tassia.pancake

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Adds basic JSON support to Pancake.
 *
 * At the time of writing, this object uses the Jackson library under the hood.
 * However this might change in the future, so don't count on it.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object JSON {

	/**
	 * The [ObjectMapper] used by this utility class.
	 */
	private val mapper = ObjectMapper()



	/**
	 * Serializes an object to a JSON string.
	 *
	 * @param data the object to serialize
	 * @param prettyPrint enable pretty-printing
	 * @return the JSON string
	 */
	fun stringify(data: Any, prettyPrint: Boolean = false): String {
		return if (prettyPrint) {
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data)
		} else {
			mapper.writeValueAsString(data)
		}
	}



	/**
	 * Reads a JSON value from the given [InputStream].
	 *
	 * @param input the input stream
	 * @param sourceClass the class of the object to be read
	 * @return the read object
	 */
	@Throws(JsonProcessingException::class)
	fun <T : Any> parse(input: InputStream, sourceClass: KClass<T>): T {
		return mapper.readValue(input, sourceClass.java)
	}

	/**
	 * Reads a JSON value from the given [String].
	 *
	 * @param input the JSON string
	 * @param sourceClass the class of the object to be read
	 * @return the read object
	 */
	@Throws(JsonProcessingException::class)
	fun <T : Any> parse(input: String, sourceClass: KClass<T>): T {
		return mapper.readValue(input, sourceClass.java)
	}

}
