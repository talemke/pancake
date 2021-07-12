package net.tassia.pancake;

import net.tassia.pancake.config.BootConfiguration;
import net.tassia.pancake.logging.Logging;

import java.util.logging.Logger;

/**
 * The main class for Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public class Pancake {

	/**
	 * The currently active Pancake instance.
	 */
	public static Pancake INSTANCE;

	/**
	 * The boot configuration.
	 */
	public final BootConfiguration bootConfig;

	/**
	 * The internal core logger.
	 */
	private final Logger logger;





	/**
	 * Creates a new Pancake instance.
	 */
	public Pancake(BootConfiguration config) {

		// Assure there aren't multiple instances
		if (Pancake.INSTANCE != null) {
			throw new IllegalStateException("There is already an active Pancake instance.");
		}
		Pancake.INSTANCE = this;

		// Assign fields
		this.bootConfig = config;
		this.logger = Logging.get(null);

	}





	/**
	 * Loads/Initializes the application.
	 */
	public void load() {
		logger.info("Loading...");

		// TODO
	}





	/**
	 * Starts the application.
	 */
	public void start() {
		logger.info("Starting...");

		// TODO
	}





	/**
	 * Stops the application.
	 */
	public void stop() {
		logger.info("Shutting down...");

		// TODO

		logger.info("Good bye!");
	}

}
