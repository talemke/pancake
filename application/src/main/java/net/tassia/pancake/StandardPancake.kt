package net.tassia.pancake

import kotlinx.coroutines.*
import net.tassia.pancake.database.StandardDatabase
import net.tassia.pancake.event.EventManager
import net.tassia.pancake.plugin.StandardPluginManager
import net.tassia.pancake.plugin.http.HttpPlugin
import net.tassia.pancake.scheduler.StandardScheduler
import java.util.logging.Logger
import javax.sql.DataSource

class StandardPancake(override val logger: Logger, source: DataSource) : Pancake() {

	override val database = StandardDatabase(source)
	val eventManager = EventManager()
	override val scheduler = StandardScheduler()
	override val pluginManager = StandardPluginManager(this)



	fun launch() {
		logger.info("Loading Pancake...")

		// Locate plugins
		pluginManager.locatePlugin(HttpPlugin)
		// TODO

		// Prepare file system
		// TODO

		// Load plugins
		runBlocking {
			// TODO: use scheduler
			pluginManager.loadPlugins()
		}

		// Enable plugins
		runBlocking {
			// TODO: use scheduler
			pluginManager.enablePlugins()
		}

		// Done!
		logger.info("Done! Running Pancake v$VERSION")
		scheduler.eventLoop.start()
	}

	override fun shutdown() {
		logger.info("Shutting down...")

		// Exit core event loop
		logger.fine("Cancelling asynchronous jobs...")
		runBlocking {
			scheduler.eventLoop.cancelAndJoin()
		}

		// Disable plugins
		runBlocking {
			// TODO: use scheduler
			pluginManager.disablePlugins()
		}

		// Terminate core event loop
		logger.fine("Terminating scheduler...")
		scheduler.eventLoop.cancel()

		// Flush caches
		logger.fine("Flushing caches...")
		// TODO

		// Done!
		logger.info("Good bye!")
	}

}
