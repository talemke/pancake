package net.tassia.pancake.util

import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.reflect.KClass

actual object JSON {

	private val mapper = ObjectMapper()

	actual fun stringify(any: Any, prettyPrint: Boolean): String {
		return if (prettyPrint) {
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(any)
		} else {
			mapper.writeValueAsString(any)
		}
	}

	actual fun <T : Any> parse(json: String, sourceClass: KClass<T>): T {
		return mapper.readValue(json, sourceClass.java)
	}

	actual inline fun <reified T : Any> parse(json: String): T {
		return parse(json, T::class)
	}

}
