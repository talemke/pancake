package net.tassia.pancake.logging;

import org.slf4j.impl.StaticLoggerBinder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PancakeLogger {

	private PancakeLogger() {
	}

	public static Logger createLogger() {
		// Create logger
		Logger logger = Logger.getLogger("Pancake");

		// Setup handlers
		logger.setUseParentHandlers(false);
		logger.addHandler(new LoggingHandler());

		// Setup StaticLoggerBinder for SLF4J
		StaticLoggerBinder.REDIRECT_TO = logger;

		// Set log level
		logger.setLevel(Level.FINE);

		return logger;
	}

}
