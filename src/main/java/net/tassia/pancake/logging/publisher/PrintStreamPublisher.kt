package net.tassia.pancake.logging.publisher

import net.tassia.pancake.logging.LogRecord
import net.tassia.pancake.logging.formatter.LogFormatter
import java.io.PrintStream

/**
 * This publisher writes a log record to a PrintStream.
 *
 * @param stream the stream to write to (e.g. [System.out])
 * @param formatter the formatter to use
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PrintStreamPublisher(

	private val stream: PrintStream,
	private val formatter: LogFormatter,

) : LogPublisher {

	override fun publish(record: LogRecord) = stream.println(formatter.format(record))
	override fun flush() = stream.flush()
	override fun close() = stream.close()

}
