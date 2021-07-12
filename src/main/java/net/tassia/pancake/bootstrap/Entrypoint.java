package net.tassia.pancake.bootstrap;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.PancakeException;
import net.tassia.pancake.logging.Logging;

/**
 * The entrypoint class for the JVM.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Entrypoint {

	/**
	 * The entrypoint method for the JVM.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		// Create loggers
		Logging.init(args);

		// Start application
		try {
			Entrypoint.start(args);
		} catch (Throwable ex) {
			// TODO
			ex.printStackTrace();
			System.exit(1);
		}

		// Close loggers
		Logging.flushLoggers();
		Logging.closeLoggers();

		// Exit application
		System.exit(0);
	}





	/**
	 * Starts the actual application.
	 *
	 * @param args command-line arguments
	 */
	public static void start(String[] args) {
		// Initialize
		Pancake pancake = new Pancake();

		// Load
		try {
			pancake.load(args);
		} catch(Throwable ex) {
			throw new PancakeException("An unexpected error occurred while loading Pancake.", ex);
		}

		// Start
		try {
			pancake.start();
		} catch(Throwable ex) {
			throw new PancakeException("An unexpected error occurred while starting Pancake.", ex);
		}

		// Run CLI
		// TODO

		// Shutdown
		try {
			pancake.stop();
		} catch(Throwable ex) {
			throw new PancakeException("An unexpected error occurred while shutting down Pancake.", ex);
		}
	}





	/**
	 * Static class.
	 */
	private Entrypoint() {
	}

}
