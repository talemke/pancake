package net.tassia.pancake.config

import java.io.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.javaSetter

/**
 * Used to load and save configs. The configs are analyzed using Reflection and the [ConfigEntry] annotation.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object ConfigIO {

	/**
	 * A set containing all [ConfigDataType]s. The set is mutable to allow for overriding behavior
	 * on certain types or adding your own ones.
	 */
	val dataTypes: MutableSet<ConfigDataType<*>> = mutableSetOf(
		BooleanDataType, IntDataType, LongDataType, FloatDataType, DoubleDataType,
		StringDataType, UUIDDataType, LoggingLevelDataType
	)



	/**
	 * Saves the given config to a file.
	 *
	 * @param file the file to save to
	 * @param config the config to save
	 * @param driver the driver
	 * @param commenter the commenter
	 */
	fun save(file: File, config: Any, driver: ConfigDriver, commenter: ConfigCommenter = ConfigCommenter.NOOP) {
		FileWriter(file).use { save(it, config, driver, commenter) }
	}

	/**
	 * Writes the given config.
	 *
	 * @param writer the writer
	 * @param config the config
	 * @param driver the driver
	 * @param commenter the commenter
	 */
	fun save(writer: Writer, config: Any, driver: ConfigDriver, commenter: ConfigCommenter) {
		// Build map
		val map = mutableMapOf<String, String>()

		// Fill properties
		for (field in config::class.memberProperties) {
			val fieldName = field.name

			// Check for annotation
			val info = field.findAnnotation<ConfigEntry>() ?: continue

			// Get value
			val data: Any = (field as KProperty1<Any, *>).get(config) ?: continue

			// Valid type?
			if (data is Enum<*>) {
				map[info.path] = data.name
			} else {
				val dataType = dataTypes.find { it.type.createType() == field.returnType }
				if (dataType != null) {
					map[info.path] = (dataType as ConfigDataType<Any>).write(data)
				} else {
					throw IllegalArgumentException("Property $fieldName has unknown type: ${field.returnType}")
				}
			}
		}

		// Save config
		driver.write(writer, map, commenter)
	}



	/**
	 * Loads the configuration from the given file.
	 *
	 * @param file the file to load from
	 * @param config the config to load
	 * @param driver the driver
	 * @return the config
	 */
	fun <T : Any> load(file: File, config: T, driver: ConfigDriver): T {
		return FileReader(file).use { load(it, config, driver) }
	}

	/**
	 * Loads the configuration from the given reader.
	 *
	 * @param reader the reader
	 * @param config the config to load
	 * @param driver the driver
	 * @return the config
	 */
	fun <T : Any> load(reader: Reader, config: T, driver: ConfigDriver): T {
		// Load actual config
		val map = driver.read(reader)

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
			val data: String = map[info.path] ?: continue


			// Valid type?
			if (field.javaGetter!!.returnType.isEnum) {
				field.setter.call(config, field.javaGetter!!.returnType.getMethod("valueOf").invoke(null, data))
			} else {
				val dataType = dataTypes.find { it.type.createType() == field.returnType }
				if (dataType != null) {
					field.setter.call(config, dataType.read(data))
				} else {
					throw IllegalArgumentException("Property $fieldName has unknown type: ${field.returnType}")
				}
			}
		}
		return config
	}

}
