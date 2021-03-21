package net.tassia.pancake.logging.formatter

import net.tassia.pancake.logging.Level
import net.tassia.pancake.logging.LogRecord
import net.tassia.pancake.plugin.Plugin
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.util.*

object DefaultFormatter : LogFormatter {

	private const val FORMAT = "%s | %s: %s"
	private const val FORMAT_PLUGIN = "%s | %s | %s: %s"

	override fun format(record: LogRecord): String {
		val message = record.message?.let {
			formatLines(record.timestamp, record.level, it, record.plugin)
		} ?: ""

		val stackTrace = record.error?.let { error ->
			StringWriter().let {
				error.printStackTrace(PrintWriter(it))
				it.toString()
			}
		} ?: ""
		val error = formatLines(record.timestamp, record.level, stackTrace, record.plugin)

		return (message + "\n" + error).trim()
	}

	private fun formatLines(timestamp: Long, level: Level, message: String, plugin: Plugin?): String {
		if (message.isBlank()) return ""
		val lines = message.replace("\r", "").split("\n")
		return lines.joinToString("\n") {
			if (plugin != null) {
				withPlugin(timestamp, level, it, plugin)
			} else {
				noPlugin(timestamp, level, it)
			}
		}
	}



	private val dateFormat = DateFormat.getDateTimeInstance()

	private fun timestamp(timestamp: Long): String {
		return dateFormat.format(Date(timestamp))
	}



	private fun withPlugin(timestamp: Long, level: Level, message: String, plugin: Plugin): String {
		return FORMAT_PLUGIN.format(timestamp(timestamp), plugin.info.id, level.name, message)
	}

	private fun noPlugin(timestamp: Long, level: Level, message: String): String {
		return FORMAT.format(timestamp(timestamp), level.name, message)
	}

}
