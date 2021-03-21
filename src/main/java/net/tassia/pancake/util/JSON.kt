package net.tassia.pancake.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.reflect.KClass

/**
 * Allows to read/write JSON objects.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object JSON {

	/**
	 * The internal object mapper.
	 */
	private val mapper = ObjectMapper()



	/**
	 * Converts the given object to a JSON string.
	 *
	 * @param any the object
	 * @param prettyPrint whether pretty-print should be enabled
	 * @return the JSON string
	 */
	fun stringify(any: Any, prettyPrint: Boolean = false): String {
		return if (prettyPrint) {
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(any)
		} else {
			mapper.writeValueAsString(any)
		}
	}


	/**
	 * Parses the given JSON string to an instance of the given source class.
	 *
	 * @param json the JSON string
	 * @param sourceClass the source class
	 * @return the parsed object
	 */
	@Throws(JsonMappingException::class, JsonProcessingException::class)
	fun <T : Any> parse(json: String, sourceClass: KClass<T>): T {
		return mapper.readValue(json, sourceClass.java)
	}

	/**
	 * Parses the given JSON string to an instance.
	 *
	 * @param json the JSON string
	 * @return the parsed object
	 */
	@Throws(JsonMappingException::class, JsonProcessingException::class)
	inline fun <reified T : Any> parse(json: String): T {
		return parse(json, T::class)
	}

}
