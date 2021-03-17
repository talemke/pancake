package net.tassia.pancake.io

import java.io.PrintStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.util.*
import java.util.logging.Handler
import java.util.logging.LogRecord

/**
 * This is a logging [Handler] for printing logs to a given [PrintStream].
 *
 * @param out the target print stream
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PrintStreamLoggingHandler(private val out: PrintStream) : Handler() {

	override fun publish(record: LogRecord?) {
		if (record == null) return

		val time = DateFormat.getDateTimeInstance().format(Date(record.millis))
		val level = record.level.name
		val message = record.message

		log(time, level, message)

		record.thrown?.let {

			val stackTrace = StringWriter().run {
				it.printStackTrace(PrintWriter(this))
				return@run this.toString()
			}

			log(time, level, "${it::class.simpleName}: ${it.message}")
			log(time, level, stackTrace)

		}
	}

	override fun flush() = out.flush()
	override fun close() = out.close()

	private fun log(time: String, level: String, message: String) = message.split("\n").forEach {
		out.println("$time | $level | $it")
	}

}
