package net.tassia.pancake.config

import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * The BootConfiguration contains configurable information about how the application should be launched.
 * This includes the port numbers, logging levels and database credentials. Everything else, which needs
 * to be synchronized across different nodes is configured in the database.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class BootConfiguration {

	@PropertyName("HTTP_BIND_PORT")
	@CommandLineOverride("bind-http-port")
	var bindHttpPort: Int = 8080

	@PropertyName("HTTP_BIND_ADDRESS")
	@CommandLineOverride("bind-http-address")
	var bindHttpAddress: String = "0.0.0.0"





	fun load(propsFile: File) {
		require(propsFile.exists())
		val props = Properties().also {
			FileInputStream(propsFile).use { stream -> it.load(stream) }
		}
		load(props)
	}

	private fun load(props: Properties) {
		for (prop in this::class.memberProperties) {
			val name = prop.findAnnotation<PropertyName>() ?: continue
			val value = (props[name.value] as String?) ?: continue
			assignValue(prop, value)
		}
	}

	fun checkOverrides(args: Collection<String>) {
		for (prop in this::class.memberProperties) {
			val name = prop.findAnnotation<PropertyName>() ?: continue
			val override = args.find { it.startsWith("--${name.value}=") } ?: continue
			assignValue(prop, override.substring("--${name.value}=".length))
		}
	}

	@Suppress("UNCHECKED_CAST")
	private fun assignValue(prop: KProperty1<out BootConfiguration, *>, value: String) {
		require(prop is KMutableProperty1)
		when (prop.returnType) {
			Int::class -> {
				// Set integer
				val intValue = value.toInt()
				(prop as KMutableProperty1<BootConfiguration, Int>).set(this, intValue)

			}
			String::class -> {
				// Set string
				(prop as KMutableProperty1<BootConfiguration, String>).set(this, value)

			}
			else -> throw IllegalStateException("${prop.name} has unsupported type: ${prop.returnType}")
		}
	}

}
