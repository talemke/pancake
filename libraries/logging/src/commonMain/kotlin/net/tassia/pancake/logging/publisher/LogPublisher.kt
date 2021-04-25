package net.tassia.pancake.logging.publisher

import net.tassia.pancake.logging.LogRecord

/**
 * "Publishes" a log, e.g. by writing it into an OutputStream.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface LogPublisher<DATA : Any> {

	/**
	 * Called when a new log should be published.
	 */
	fun publish(record: LogRecord<DATA>)

	/**
	 * Called when the publisher should be explicitly flushed.
	 */
	fun flush() = Unit

	/**
	 * Called when the publisher should be closed.
	 */
	fun close() = Unit

}
