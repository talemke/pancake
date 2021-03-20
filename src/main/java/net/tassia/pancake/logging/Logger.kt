package net.tassia.pancake.logging

import net.tassia.pancake.logging.publisher.LogPublisher
import net.tassia.pancake.plugin.Plugin

/**
 * Used for logging.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Logger {

	/**
	 * A set of all currently active publishers.
	 */
	val publishers: MutableSet<LogPublisher> = mutableSetOf()



	/**
	 * Logs a new record with the given goal, level, message and error.
	 *
	 * @param plugin the plugin that produced the log, can be `null`
	 * @param level the log level
	 * @param message the message, can be `null`
	 * @param error the error, can be `null`
	 * @return the generated log record
	 */
	fun log(plugin: Plugin?, level: Level, message: String?, error: Throwable?): LogRecord {
		val record = LogRecord(System.currentTimeMillis(), plugin, level, message, error)
		publishers.forEach { it.publish(record) }
		return record
	}



	/**
	 * Explicitly flushes all registered [LogPublisher]s.
	 */
	fun flush() = publishers.forEach(LogPublisher::flush)

	/**
	 * Closes all registered [LogPublisher]s.
	 */
	fun close() = publishers.forEach(LogPublisher::close)



	/**
	 * Logs a message on the [Level.INFO] level.
	 *
	 * @param message the message, can be `null`
	 * @param error the error, can be `null`
	 * @param plugin the plugin that produced the log, can be `null`
	 * @return the generated log record
	 */
	fun info(message: String? = null, error: Throwable? = null, plugin: Plugin? = null): LogRecord
		= log(plugin, Level.INFO, message, error)

	/**
	 * Logs a message on the [Level.DEBUG] level.
	 *
	 * @param message the message, can be `null`
	 * @param error the error, can be `null`
	 * @param plugin the plugin that produced the log, can be `null`
	 * @return the generated log record
	 */
	fun debug(message: String? = null, error: Throwable? = null, plugin: Plugin? = null): LogRecord
		= log(plugin, Level.DEBUG, message, error)

	/**
	 * Logs a message on the [Level.WARNING] level.
	 *
	 * @param message the message, can be `null`
	 * @param error the error, can be `null`
	 * @param plugin the plugin that produced the log, can be `null`
	 * @return the generated log record
	 */
	fun warn(message: String? = null, error: Throwable? = null, plugin: Plugin? = null): LogRecord
		= log(plugin, Level.WARNING, message, error)

	/**
	 * Logs a message on the [Level.ERROR] level.
	 *
	 * @param message the message, can be `null`
	 * @param error the error, can be `null`
	 * @param plugin the plugin that produced the log, can be `null`
	 * @return the generated log record
	 */
	fun error(message: String? = null, error: Throwable? = null, plugin: Plugin? = null): LogRecord
		= log(plugin, Level.ERROR, message, error)

	/**
	 * Logs a message on the [Level.SEVERE] level.
	 *
	 * @param message the message, can be `null`
	 * @param error the error, can be `null`
	 * @param plugin the plugin that produced the log, can be `null`
	 * @return the generated log record
	 */
	fun severe(message: String? = null, error: Throwable? = null, plugin: Plugin? = null): LogRecord
		= log(plugin, Level.SEVERE, message, error)

}
