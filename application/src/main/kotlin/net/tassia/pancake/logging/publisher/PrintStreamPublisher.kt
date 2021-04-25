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
open class PrintStreamPublisher<DATA : Any>(

	open val stream: PrintStream,
	open val formatter: LogFormatter<DATA>,

) : LogPublisher<DATA> {

	override fun publish(record: LogRecord<DATA>) = stream.println(formatter.format(record))
	override fun flush() = stream.flush()
	override fun close() = stream.close()

}
