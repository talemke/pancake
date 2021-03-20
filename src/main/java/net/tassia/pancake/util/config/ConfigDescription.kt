package net.tassia.pancake.util.config

/**
 * Adds a description to a [ConfigEntry].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigDescription(

	/**
	 * The description.
	 */
	val description: String

)
