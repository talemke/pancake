package net.tassia.pancake.config

/**
 * Defines what key to this value is associated with when parsing a properties file.
 *
 * @param value the key, usually in all caps
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class PropertyName(val section: String, val name: String)
