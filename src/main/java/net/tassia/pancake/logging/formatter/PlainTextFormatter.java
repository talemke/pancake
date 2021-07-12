package net.tassia.pancake.logging.formatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Default formatter to format logs into an easily readable string.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public class PlainTextFormatter extends Formatter {

	/**
	 * The date format used.
	 */
	private final DateFormat format = new SimpleDateFormat("HH':'mm':'ss'.'SSS");

	@Override
	public String format(LogRecord record) {
		// Create builder
		StringBuilder builder = new StringBuilder();

		// Build message
		buildPrefix(builder, record);
		builder.append(record.getMessage());

		// Build error
		Throwable thrown = record.getThrown();
		if (thrown != null) {
			builder.append(System.lineSeparator());
			buildPrefix(builder, record);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			thrown.printStackTrace(pw);
			builder.append(sw);
		}

		// Done!
		return builder.toString();
	}

	/**
	 * Appends the prefix to the StringBuilder.
	 *
	 * @param builder the StringBuilder to append to
	 * @param record information about the log
	 */
	private void buildPrefix(StringBuilder builder, LogRecord record) {
		builder.append(format.format(new Date(record.getMillis())));
		builder.append(" | ");
		builder.append(record.getLoggerName());
		builder.append('@');
		builder.append(record.getLevel().getLocalizedName());
		builder.append(": ");
	}

}
