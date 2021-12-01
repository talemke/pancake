package net.tassia.pancake.plugin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import net.tassia.pancake.database.Transaction
import net.tassia.pancake.event.Event
import kotlin.reflect.KClass

abstract class PluginManager {

	abstract fun findPlugin(name: String): Plugin
	abstract fun findPluginOrNull(name: String): Plugin?

	abstract fun getLoadedPlugins(): Set<Plugin>



	abstract suspend fun <T> transaction(plugin: Plugin, block: suspend (Transaction) -> T): T



	abstract suspend fun enablePlugin(name: String)
	abstract suspend fun enablePlugin(plugin: Plugin)

	abstract suspend fun disablePlugin(name: String)
	abstract suspend fun disablePlugin(plugin: Plugin)

	abstract fun isEnabled(name: String): Boolean
	abstract fun isEnabled(plugin: Plugin): Boolean



	abstract suspend fun installPlugin(name: String)
	abstract suspend fun installPlugin(plugin: Plugin)

	abstract suspend fun uninstallPlugin(name: String)
	abstract suspend fun uninstallPlugin(plugin: Plugin)

	abstract suspend fun upgradePlugin(plugin: Plugin, range: IntRange)
	abstract suspend fun downgradePlugin(plugin: Plugin, range: IntRange)

	abstract fun isInstalled(name: String): Boolean
	abstract fun isInstalled(plugin: Plugin): Boolean

	abstract fun getInstalledVersion(name: String): Int
	abstract fun getInstalledVersion(plugin: Plugin): Int



	abstract fun <E : Event> listenSync(plugin: Plugin, event: KClass<E>, block: (E) -> Unit)
	abstract fun <E : Event> listenAsync(plugin: Plugin, event: KClass<E>, block: suspend (E) -> Unit)

	abstract fun <E : Event> callEvent(plugin: Plugin, klass: KClass<E>, event: E): Job
	abstract fun <E : Event> callEventAsync(plugin: Plugin, klass: KClass<E>, event: E): Deferred<Unit>
	abstract suspend fun <E : Event> callEventSync(plugin: Plugin, klass: KClass<E>, event: E)

}
