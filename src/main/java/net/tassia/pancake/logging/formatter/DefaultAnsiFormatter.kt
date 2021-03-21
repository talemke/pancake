package net.tassia.pancake.logging.formatter

import net.tassia.pancake.logging.Level
import net.tassia.pancake.logging.LogRecord
import net.tassia.pancake.util.ANSI

object DefaultAnsiFormatter : LogFormatter {

	private fun getAnsi(level: Level): String {
		return when (level) {
			Level.INFO -> ANSI.PRIMARY
			Level.DEBUG -> ANSI.SECONDARY
			Level.WARNING -> ANSI.WARNING
			Level.ERROR -> ANSI.FAILURE
			Level.SEVERE -> ANSI.FAILURE
		}
	}

	override fun format(record: LogRecord): String {
		return getAnsi(record.level) + DefaultFormatter.format(record) + ANSI.RESET
	}

}
