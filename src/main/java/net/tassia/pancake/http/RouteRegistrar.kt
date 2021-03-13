package net.tassia.pancake.http

import kotlin.reflect.KClass

/**
 * This class is used to register routes.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class RouteRegistrar {

	/**
	 * Registers a new JSON `GET` route.
	 *
	 * @param path the path
	 * @param function the function
	 */
	fun get(path: String, function: (HttpTransaction<Nothing>) -> Any?) {
		TODO()
	}

	/**
	 * Registers a new JSON `POST` route.
	 *
	 * @param path the path
	 * @param requestType the request type
	 * @param function the function
	 */
	fun <T : Any> post(path: String, requestType: KClass<T>, function: (HttpTransaction<T>) -> Any?) {
		TODO()
	}

}
