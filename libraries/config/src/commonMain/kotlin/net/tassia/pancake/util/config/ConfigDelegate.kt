package net.tassia.pancake.util.config

import kotlin.reflect.KProperty

/**
 * Utility delegate used to build [Configuration]s with ease.
 *
 * @since PancakeConfig 1.0
 * @author Tassilo
 */
class ConfigDelegate<T : Any>(private val path: String, private val getter: (Configuration, String) -> T) {

	operator fun getValue(self: Configuration, property: KProperty<*>): T {
		return getter(self, path)
	}

	operator fun setValue(self: Configuration, property: KProperty<*>, value: T) {
		self[path] = value
	}

}
