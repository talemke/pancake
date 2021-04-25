package net.tassia.pancake

import net.tassia.pancake.api.event.EventManager
import net.tassia.pancake.config.PancakeConfig
import net.tassia.pancake.event.PancakePostInitEvent
import net.tassia.pancake.plugin.PluginManager
import net.tassia.pancake.util.System
import net.tassia.pancake.util.Version

class Pancake(val config: PancakeConfig, val version: Version) {

	val plugins: PluginManager = PluginManager(this)
	val events: EventManager = EventManager()



	fun init() {
		// Initialize
		Logger.info("Initializing Pancake...")

		// Locate plugins
		plugins.locatePlugins()

		// Load plugins
		plugins.loadPlugins()

		// Enable plugins
		plugins.enablePlugins()

		// Done!
		Logger.info("Done! Running Pancake/${version.toDisplayString()}")

		// Call PostInit event
		events.call(PancakePostInitEvent(this))
	}

	fun exit(status: Int) {
		// Disable plugins
		plugins.disablePlugins()

		// Flush and close logger
		Logger.flush()
		Logger.close()

		// Exit the running process
		System.exit(status)
	}

}
