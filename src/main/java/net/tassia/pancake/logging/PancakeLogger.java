package net.tassia.pancake.logging;

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

		// Set log level
		logger.setLevel(Level.FINE);

		return logger;
	}

}
