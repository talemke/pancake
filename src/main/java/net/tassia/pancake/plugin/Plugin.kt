package net.tassia.pancake.plugin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import net.tassia.pancake.Pancake
import net.tassia.pancake.database.Transaction
import net.tassia.pancake.event.Event
import java.util.logging.Logger

abstract class Plugin(val pancake: Pancake, val info: PluginInformation) {

	abstract suspend fun onLoad()
	abstract suspend fun onEnable()
	abstract suspend fun onDisable()

	abstract suspend fun onUpgrade(transaction: Transaction, from: Int, to: Int)
	abstract suspend fun onDowngrade(transaction: Transaction, from: Int, to: Int)



	val logger: Logger = Logger.getLogger("Pancake:Plugin:" + info.name).also {
		it.parent = pancake.logger
	}



	val isLoaded: Boolean
		get() = pancake.pluginManager.isLoaded(this)

	val isEnabled: Boolean
		get() = pancake.pluginManager.isEnabled(this)

	val isInstalled: Boolean
		get() = pancake.pluginManager.isInstalled(this)

	val installedVersion: Int
		get() = pancake.pluginManager.getInstalledVersion(this)



	suspend fun <T> transaction(block: suspend (Transaction) -> T): T {
		return pancake.pluginManager.transaction(this, block)
	}



	inline fun <reified E : Event> listenSync(noinline block: (E) -> Unit) {
		pancake.pluginManager.listenSync(this, E::class, block)
	}

	inline fun <reified E : Event> listenAsync(noinline block: suspend (E) -> Unit) {
		pancake.pluginManager.listenAsync(this, E::class, block)
	}



	inline fun <reified E : Event> callEvent(event: E): Job {
		return pancake.pluginManager.callEvent(this, E::class, event)
	}

	inline fun <reified E : Event> callEventAsync(event: E): Deferred<Unit> {
		return pancake.pluginManager.callEventAsync(this, E::class, event)
	}

}
