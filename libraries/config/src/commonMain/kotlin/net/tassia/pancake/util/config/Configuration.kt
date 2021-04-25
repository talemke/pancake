package net.tassia.pancake.util.config

/**
 * Represents a basic configuration.
 *
 * @since PancakeConfig 1.0
 * @author Tassilo
 */
open class Configuration {

	/**
	 * The internal storage map for all values.
	 */
	private val internal: MutableMap<String, Any?> = mutableMapOf()



	operator fun get(key: String): Any? {
		return internal[key]
	}

	operator fun set(key: String, value: Any?) {
		if (value != null) {
			internal[key] = value
		} else {
			internal.remove(key)
		}
	}



	fun getBoolean(key: String): Boolean {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is Boolean) { "Config value $key must be a boolean." }
		return value
	}

	fun getInt(key: String): Int {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is Int) { "Config value $key must be an int." }
		return value
	}

	fun getLong(key: String): Long {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is Long) { "Config value $key must be a long." }
		return value
	}

	fun getFloat(key: String): Float {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is Float) { "Config value $key must be a float." }
		return value
	}

	fun getDouble(key: String): Double {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is Double) { "Config value $key must be a double." }
		return value
	}

	fun getString(key: String): String {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is String) { "Config value $key must be a string." }
		return value
	}

	inline fun <reified T : Enum<T>> getEnum(key: String): T {
		val value = this[key]
		require(value != null) { "Config value $key must be non-null." }
		require(value is T) { "Config value $key must be enum ${T::class.simpleName}." }
		return value
	}



	fun boolean(path: String): ConfigDelegate<Boolean> {
		return ConfigDelegate(path, Configuration::getBoolean)
	}

	fun int(path: String): ConfigDelegate<Int> {
		return ConfigDelegate(path, Configuration::getInt)
	}

	fun long(path: String): ConfigDelegate<Long> {
		return ConfigDelegate(path, Configuration::getLong)
	}

	fun float(path: String): ConfigDelegate<Float> {
		return ConfigDelegate(path, Configuration::getFloat)
	}

	fun double(path: String): ConfigDelegate<Double> {
		return ConfigDelegate(path, Configuration::getDouble)
	}

	fun string(path: String): ConfigDelegate<String> {
		return ConfigDelegate(path, Configuration::getString)
	}

	inline fun <reified T : Enum<T>> enum(path: String): ConfigDelegate<T> {
		return ConfigDelegate(path, Configuration::getEnum)
	}

}
