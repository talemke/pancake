package net.tassia.pancake;

import net.tassia.event.EventManager;
import net.tassia.event.impl.EventManagerImpl;
import net.tassia.pancake.config.BootConfiguration;
import net.tassia.pancake.logging.Logging;

import java.util.logging.Logger;

/**
 * The main class for Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Pancake {

	/**
	 * The current version of Pancake.
	 */
	public static final Version VERSION = Version.fromResource(Resources.VERSION);

	/**
	 * The currently active Pancake instance.
	 */
	public static Pancake INSTANCE;

	/**
	 * The boot configuration.
	 */
	public final BootConfiguration bootConfig;

	/**
	 * The event manager.
	 */
	public final EventManager events;

	/**
	 * The internal core logger.
	 */
	private final Logger logger;





	/**
	 * Creates a new Pancake instance.
	 */
	public Pancake() {

		// Assure there aren't multiple instances
		if (Pancake.INSTANCE != null) {
			throw new IllegalStateException("There is already an active Pancake instance.");
		}
		Pancake.INSTANCE = this;

		// Assign fields
		this.bootConfig = new BootConfiguration();
		this.events = new EventManagerImpl();
		this.logger = Logging.get(null);

	}





	/**
	 * Loads/Initializes the application.
	 *
	 * @param args command-line arguments
	 */
	public void load(String[] args) {
		logger.info("Loading...");

		logger.fine("Preparing installation...");
		PancakeIO.flushTemporaryDirectory();
		PancakeIO.createApplicationDirectories();

		logger.fine("Verifying installation...");
		// TODO

		logger.fine("Loading data...");
		// TODO

		logger.fine("Loading configuration...");
		loadConfig(args);

		logger.fine("Loading plugins...");
		// TODO

		logger.fine("Loading HTTP server...");
		// TODO

		logger.fine("Loading SMTP server...");
		// TODO
	}

	/**
	 * Loads the boot configuration.
	 *
	 * @param args command-line argument
	 */
	private void loadConfig(String[] args) {
		// Load from file
		// TODO

		// CommandLine overrides
		bootConfig.checkOverrides(args);
	}





	/**
	 * Starts the application.
	 */
	public void start() {
		logger.info("Starting...");

		logger.fine("Connecting to database...");
		// TODO

		logger.fine("Preparing database...");
		// TODO

		logger.fine("Starting HTTP server...");
		// TODO

		logger.fine("Enabling plugins...");
		// TODO

		logger.info("Done! Running Pancake v" + Pancake.VERSION);
	}





	/**
	 * Stops the application.
	 */
	public void stop() {
		logger.info("Shutting down...");

		logger.fine("Disabling plugins...");
		// TODO

		logger.fine("Terminating HTTP server...");
		// TODO

		logger.fine("Terminating SMTP server...");
		// TODO

		logger.info("Good bye!");
	}

}
