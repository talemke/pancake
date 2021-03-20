package net.tassia.pancake.logging.publisher

import net.tassia.pancake.logging.LogRecord

/**
 * "Publishes" a log, e.g. by writing it into an OutputStream.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface LogPublisher {

	/**
	 * Called when a new log should be published.
	 */
	fun publish(record: LogRecord)

}
