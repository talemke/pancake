package net.tassia.pancake.logging

import net.tassia.pancake.plugin.Plugin

/**
 * Represents a single log entry.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class LogRecord(

	/**
	 * The timestamp.
	 */
	val timestamp: Long,

	/**
	 * The plugin that produced the log.
	 */
	val plugin: Plugin?,

	/**
	 * The level of the log.
	 */
	val level: Level,

	/**
	 * The message attached to the log.
	 */
	val message: String?,

	/**
	 * The error attached to the log.
	 */
	val error: Throwable?,

)
