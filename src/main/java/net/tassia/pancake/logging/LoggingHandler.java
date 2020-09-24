package net.tassia.pancake.logging;

import net.tassia.pancake.Pancake;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

class LoggingHandler extends Handler {
	private final PrintStream out;

	public LoggingHandler() {
		File file = new File("logs", Long.toHexString(System.currentTimeMillis()) + ".txt");
		try {
			file.getParentFile().mkdirs();
			if (!file.exists()) file.createNewFile();
			this.out = new PrintStream(file);

			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z)").format(new Date());
			String version = String.format("%d.%d.%d, build %d - %s @ %s", Pancake.VERSION_MAJOR, Pancake.VERSION_MINOR,
				Pancake.VERSION_PATCH, Pancake.VERSION_BUILD, Pancake.VERSION_BRANCH, Pancake.VERSION_HEAD);

			out.println("Creating log file at " + date + " (0x" + Long.toHexString(System.currentTimeMillis()) + ")");
			out.println("Pancake Version: " + version);
			out.println("--------------------------------------------------");
		} catch (IOException ex) {
			throw new Error(ex);
		}
	}

	protected String getColor(Level level) {
		if (level== Level.INFO) {
			return Pancake.ANSI_INFO;
		} else if (level == Level.WARNING) {
			return Pancake.ANSI_WARNING;
		} else if (level == Level.SEVERE) {
			return Pancake.ANSI_SEVERE;
		} else if (level == Level.FINE) {
			return Pancake.ANSI_FINE;
		} else if (level == Level.FINER) {
			return Pancake.ANSI_FINE;
		} else if (level == Level.FINEST) {
			return Pancake.ANSI_FINE;
		} else {
			return "\u001B[38;2;127;127;255m";
		}
	}

	protected final String ANSI_TERMINATOR = "\u001B[0m";

	@Override
	public void publish(LogRecord record) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		if (record.getThrown() != null) {
			record.getThrown().printStackTrace();
		}

		String format = String.format("%s | #%02x | %s: %s", sdf.format(new Date(record.getMillis())),
			record.getThreadID(), record.getLevel().getName(), record.getMessage());
		System.out.println(getColor(record.getLevel()) + format + ANSI_TERMINATOR);
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
