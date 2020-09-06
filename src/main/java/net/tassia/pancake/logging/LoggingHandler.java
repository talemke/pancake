package net.tassia.pancake.logging;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

class LoggingHandler extends Handler {
	private PrintStream out;

	public LoggingHandler() {
		File file = new File("logs", new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt");
		try {
			file.getParentFile().mkdirs();
			if (!file.exists()) file.createNewFile();
			this.out = new PrintStream(file);
		} catch (IOException ex) {
			this.out = null;
			ex.printStackTrace();
		}
	}

	@Override
	public void publish(LogRecord record) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		if (record.getThrown() != null) {
			record.getThrown().printStackTrace();
		}

		String format = "%s | #%02x | %s: %s";
		format = String.format(format, sdf.format(new Date(record.getMillis())), record.getThreadID(), record.getLevel().getName(), record.getMessage());
		System.out.println(format);
		if (out != null) out.println(format);
	}

	@Override
	public void flush() {
		out.flush();
	}

	@Override
	public void close() throws SecurityException {
		out.close();
	}

}