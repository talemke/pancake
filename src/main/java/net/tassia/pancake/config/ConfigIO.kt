package net.tassia.pancake.config

import java.io.File
import java.io.FileReader
import java.io.Reader
import java.util.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * Used to load and save configs. The configs are analyzed using Reflection and the [ConfigEntry] annotation.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object ConfigIO {

	/**
	 * Saves the given config to a file.
	 *
	 * @param file the file to save to
	 * @param config the config to save
	 */
	fun save(file: File, config: Any) {
		TODO()
	}



	/**
	 * Loads the configuration from the given file.
	 *
	 * @param file the file to load from
	 * @param config the config to load
	 * @param args command-line arguments
	 * @return the config
	 */
	fun <T: Any> load(file: File, config: T, args: Array<String>): T {
		return FileReader(file).use { load(it, config, args) }
	}

	/**
	 * Loads the configuration from the given reader.
	 *
	 * @param reader the reader
	 * @param config the config to load
	 * @param args command-line arguments
	 * @return the config
	 */
	fun <T: Any> load(reader: Reader, config: T, args: Array<String>): T {
		// Load actual config
		val props = Properties()
		props.load(reader)

		// Fill properties
		for (field in config::class.memberProperties) {
			val fieldName = field.name

			// Check for annotation
			val info = field.findAnnotation<ConfigEntry>() ?: continue

			// Is accessible?
			if (field !is KMutableProperty<*>) {
				throw IllegalArgumentException("Property $fieldName is not mutable.")
			}

			// Parse property
			val data: String = let {
				val override = args.find { it.startsWith("--set-${info.path}=") }
				if (override != null) {
					return@let override.substring("--set-${info.path}=".length)
				} else {
					return@let props[info.path]?.toString()
				}
			} ?: continue


			// Valid type?
			field.setter.call(config, parseDataType(field.returnType, data))
		}
		return config
	}

	private fun parseDataType(type: KType, data: String): Any {
		return when (type) {
			Boolean::class.createType() -> data.toBoolean()
			Int::class.createType() -> data.toInt()
			Long::class.createType() -> data.toLong()
			Float::class.createType() -> data.toFloat()
			Long::class.createType() -> data.toLong()
			String::class.createType() -> data
			UUID::class.createType() -> UUID.fromString(data)
			else -> throw IllegalArgumentException("Unknown type: $type")
		}
	}

}
