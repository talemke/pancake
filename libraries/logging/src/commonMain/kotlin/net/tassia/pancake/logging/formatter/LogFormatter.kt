package net.tassia.pancake.logging.formatter

import net.tassia.pancake.logging.LogRecord

/**
 * Formats a given [LogRecord] into a string.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface LogFormatter<DATA : Any> {

	/**
	 * Formats the given log record to a string.
	 *
	 * @param record the record
	 * @return the generated string
	 */
	fun format(record: LogRecord<DATA>): String

}
