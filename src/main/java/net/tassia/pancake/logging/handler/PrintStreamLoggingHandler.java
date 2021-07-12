package net.tassia.pancake.logging.handler;

import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Logging handler that prints formatted logs to a given {@link PrintStream}.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class PrintStreamLoggingHandler extends Handler {

	/**
	 * The target stream.
	 */
	private final PrintStream stream;

	/**
	 * Constructs a new PrintStreamLoggingHandler with the given destination stream.
	 *
	 * @param stream the stream to write to
	 */
	public PrintStreamLoggingHandler(PrintStream stream) {
		this.stream = stream;
	}



	@Override
	public void publish(LogRecord record) {
		// Ignore null records
		if (record == null) return;

		// Format and print
		String formatted = this.getFormatter().format(record);
		stream.println(formatted);
	}

	@Override
	public void flush() {
		stream.flush();
	}

	@Override
	public void close() throws SecurityException {
		stream.close();
	}

}
