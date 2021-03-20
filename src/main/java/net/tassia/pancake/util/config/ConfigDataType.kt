package net.tassia.pancake.util.config

import kotlin.reflect.KClass

/**
 * Defines how a type should be parsed.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class ConfigDataType<T : Any>(

	/**
	 * The actual type.
	 */
	val type: KClass<T>,

	/**
	 * The write function.
	 */
	val write: (T) -> String,

	/**
	 * The read function.
	 */
	val read: (String) -> T

)
