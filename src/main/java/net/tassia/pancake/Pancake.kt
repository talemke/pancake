package net.tassia.pancake

import net.tassia.event.EventManager
import net.tassia.pancake.event.MailEvent
import net.tassia.pancake.event.PancakeEvent
import net.tassia.pancake.io.PancakeConfig
import net.tassia.pancake.logging.Logger
import net.tassia.pancake.plugin.PluginManager
import net.tassia.pancake.util.version.Version
import net.tassia.pancake.util.version.VersionType
import kotlin.system.exitProcess

/**
 * The base class for Pancake.
 *
 * @param config the configuration
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Pancake(val config: PancakeConfig) {

	/**
	 * Used to handle events for Pancake.
	 */
	val events = EventManager.newDefault()

	/**
	 * The [PluginManager] for Pancake.
	 */
	val plugins = PluginManager(this)



	init {
		// Setup logger
		Logger.info("Initializing Pancake...")

		// Register core events
		events.registerEvent<PancakeEvent>()
		events.registerEvent<MailEvent>()

		// Locate plugins
		plugins.locatePlugins()

		// Load plugins
		plugins.loadPlugins()

		// Enable plugins
		plugins.enablePlugins()

		// Done!
		Logger.info("Done! Running Pancake/${VERSION.toDisplayString()}")
	}



	/**
	 * Exits the Pancake application.
	 *
	 * @param status the status code (e.g. `0` for success)
	 */
	fun exit(status: Int) {
		// Disable plugins
		plugins.disablePlugins()

		// Exit the running process
		exitProcess(status)
	}



	companion object {

		/**
		 * The current version of Pancake.
		 */
		val VERSION = Version(1, 0, 0, 1, "dbd3766fc98765487e213418f5c600e027c8957a", branch = "main", type = VersionType.SNAPSHOT)

		/**
		 * The hard-coded limit of how long a single mail address ('name@hostname.tld') can be (in bytes).
		 */
		const val MAIL_ADDRESS_MAX = 255

		/**
		 * The hard-coded limit of how long a mail subject can be (in bytes).
		 */
		const val MAIL_SUBJECT_MAX = 255

		/**
		 * The hard-coded limit of how long a mails displayable content can be (in bytes).
		 */
		const val MAIL_CONTENT_MAX = Int.MAX_VALUE

		/**
		 * The hard-coded limit of how many recipients a single mail can have at maximum.
		 */
		const val MAIL_RECIPIENTS_MAX = 63

	}

}
