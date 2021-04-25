package net.tassia.pancake.util

import kotlin.reflect.KClass

actual object JSON {

	actual fun stringify(any: Any, prettyPrint: Boolean): String {
		return if (prettyPrint) {
			// TODO: Pretty print
			kotlin.js.JSON.stringify(any)
		} else {
			kotlin.js.JSON.stringify(any)
		}
	}

	actual fun <T : Any> parse(json: String, sourceClass: KClass<T>): T {
		return kotlin.js.JSON.parse(json)
	}

	actual inline fun <reified T : Any> parse(json: String): T {
		return parse(json, T::class)
	}

}
