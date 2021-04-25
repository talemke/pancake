package net.tassia.pancake.logging

/**
 * Represents a single log entry.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class LogRecord<DATA : Any>(

	/**
	 * The timestamp.
	 */
	val timestamp: Long,

	/**
	 * The plugin that produced the log.
	 */
	val data: DATA?,

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
