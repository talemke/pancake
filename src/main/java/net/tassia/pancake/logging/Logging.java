package net.tassia.pancake.logging;

import net.tassia.pancake.PancakeIO;
import net.tassia.pancake.PancakeIOException;
import net.tassia.pancake.logging.formatter.AnsiLogFormatter;
import net.tassia.pancake.logging.formatter.PlainTextFormatter;
import net.tassia.pancake.logging.handler.PrintStreamLoggingHandler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class, used for managing loggers.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Logging {

	/**
	 * An array containing all currently registered loggers.
	 * This array is resized automatically when a new logger is added.
	 */
	private static Logger[] active = new Logger[0];

	/**
	 * A {@link PrintStream} to the text log file.
	 */
	private static PrintStream textFileStream = null;

	/**
	 * A {@link DataOutputStream} to the binary log file.
	 */
	private static DataOutputStream binaryFileStream = null;

	/**
	 * Whether batchMode is activated (i.e. no ANSI color codes).
	 */
	private static boolean batchMode = false;

	/**
	 * The target logging level.
	 */
	private static Level logLevel = Level.FINE;



	/**
	 * Initializes the logging facilities with the given command-line arguments.
	 *
	 * @param args command-line arguments
	 */
	public static void init(String[] args) {
		long time = System.currentTimeMillis();
		File textFile = PancakeIO.getTextLogFile(time);
		File binaryFile = PancakeIO.getBinaryLogFile(time);

		for (String arg : args) {
			if (arg.startsWith("--log-level=")) {
				logLevel = Level.parse(arg.substring("--log-level=".length()));
			} else if (arg.startsWith("--batch-mode=")) {
				batchMode = Boolean.parseBoolean(arg.substring("--batch-mode=".length()));
			} else if (arg.equals("--batch-mode")) {
				batchMode = true;
			}
		}

		try {
			PancakeIO.createFile(textFile);
			Logging.textFileStream = new PrintStream(textFile);
		} catch (Throwable ex) {
			throw new PancakeIOException("Failed to open text log file.", ex);
		}

		try {
			PancakeIO.createFile(binaryFile);
			FileOutputStream fos = new FileOutputStream(binaryFile);
			Logging.binaryFileStream = new DataOutputStream(fos);
		} catch (Throwable ex) {
			throw new PancakeIOException("Failed to open text log file.", ex);
		}

		reloadLoggers();
	}



	/**
	 * Returns a logger for the given module.
	 *
	 * @param name the module name, or <code>null</code> if core
	 * @return the logger
	 */
	public static Logger get(String name) {
		name = name != null ? "Pancake:" + name : "Pancake";
		for (Logger logger : Logging.active) {
			if (logger.getName().equals(name)) {
				return logger;
			}
		}

		Logger logger = Logger.getLogger(name);
		reloadLogger(logger);

		Logger[] temp = Logging.active;
		Logging.active = new Logger[temp.length + 1];
		System.arraycopy(temp, 0, Logging.active, 0, temp.length);
		Logging.active[Logging.active.length - 1] = logger;

		return logger;
	}



	/**
	 * Flushes all registered loggers.
	 */
	public static void flushLoggers() {
		for (Logger logger : Logging.active) {
			for (Handler handler : logger.getHandlers()) {
				handler.flush();
			}
		}
	}

	/**
	 * Closes all registered loggers.
	 */
	public static void closeLoggers() {
		Logger[] var0 = Logging.active;
		Logging.active = new Logger[0];

		for (Logger logger : var0) {
			for (Handler handler : logger.getHandlers()) {
				handler.close();
			}
		}
	}



	/**
	 * Reloads all currently active loggers.
	 */
	private static void reloadLoggers() {
		for (Logger logger : Logging.active) {
			reloadLogger(logger);
		}
	}

	/**
	 * Reloads the given logger.
	 *
	 * @param logger the logger
	 */
	private static void reloadLogger(Logger logger) {
		// Settings
		logger.setLevel(Logging.logLevel);
		logger.setUseParentHandlers(false);

		// Create console handler
		PrintStreamLoggingHandler consoleHandler = new PrintStreamLoggingHandler(System.out);
		if (batchMode) {
			consoleHandler.setFormatter(new PlainTextFormatter());
		} else {
			consoleHandler.setFormatter(new AnsiLogFormatter());
		}
		logger.addHandler(consoleHandler);

		// Create text file handler
		PrintStreamLoggingHandler textFileHandler = new PrintStreamLoggingHandler(textFileStream);
		textFileHandler.setFormatter(new PlainTextFormatter());
		logger.addHandler(textFileHandler);

		// Create binary file handler
		// TODO
	}



	/**
	 * Static class.
	 */
	private Logging() {
	}

}
