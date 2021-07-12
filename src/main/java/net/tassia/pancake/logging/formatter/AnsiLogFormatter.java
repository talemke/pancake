package net.tassia.pancake.logging.formatter;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Formats logs using {@link PlainTextFormatter}, but wraps them in ANSI color codes
 * for each specific level.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class AnsiLogFormatter extends PlainTextFormatter {

	@Override
	public String format(LogRecord record) {
		// ANSI prefix
		String prefix;
		Level level = record.getLevel();
		if (level == Level.FINE || level == Level.FINER || level == Level.FINEST) {
			prefix = "\u001B[38;2;127;127;127m"; // Gray
		} else if (level == Level.WARNING) {
			prefix = "\u001b[33m"; // Yellow
		} else if (level == Level.SEVERE) {
			prefix = "\u001b[31m"; // Red
		} else {
			prefix = ""; // Default color
		}

		// Prefix + Message + Ansi Terminator
		return prefix + super.format(record) + "\u001b[0m";
	}

}
