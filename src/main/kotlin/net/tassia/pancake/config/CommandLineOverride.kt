package net.tassia.pancake.config

/**
 * A configuration property annotated with this can be overridden by a command-line argument.
 *
 * For example:
 * ```kotlin
 * @CommandLineOverride("port")
 * var port: Int = 8080
 * ```
 * can be overridden using `--port=6060` when launching the application.
 *
 * CommandLine-overrides have a higher priority than the configuration file.
 *
 * @param value the name to be supplied in the command-line argument (with no leading '--' and no trailing '=')
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class CommandLineOverride(val value: String)
