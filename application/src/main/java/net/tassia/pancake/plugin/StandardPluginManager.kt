package net.tassia.pancake.plugin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import net.tassia.pancake.StandardPancake
import net.tassia.pancake.database.Transaction
import net.tassia.pancake.event.AsyncListener
import net.tassia.pancake.event.Event
import net.tassia.pancake.event.SyncListener
import kotlin.reflect.KClass

class StandardPluginManager(private val pancake: StandardPancake) : PluginManager() {

	private var available: List<PluginInformation> = emptyList()
	private var plugins: Set<Plugin> = emptySet()

	private var isLoaded: Set<Plugin> = emptySet()
	private var isEnabled: Set<Plugin> = emptySet()



	override fun findPlugin(name: String): Plugin {
		return findPluginOrNull(name) ?: throw IllegalArgumentException("Plugin '$name' was not found.")
	}

	override fun findPluginOrNull(name: String): Plugin? {
		return plugins.find { it.info.name == name }
	}

	private fun findPluginByID(identifier: String): Plugin {
		return findPluginByIDOrNull(identifier) ?: throw IllegalArgumentException("Plugin '$identifier' was not found.")
	}

	private fun findPluginByIDOrNull(identifier: String): Plugin? {
		return plugins.find { it.info.identifier == identifier }
	}

	override fun findPlugin(info: PluginInformation): Plugin {
		return findPluginOrNull(info) ?: throw IllegalArgumentException("Plugin '${info.name}' was not found.")
	}

	override fun findPluginOrNull(info: PluginInformation): Plugin? {
		return plugins.find { it.info == info }
	}





	override fun getLoadedPlugins(): Set<Plugin> {
		return plugins.toSet()
	}

	override fun getLoadOrder(): List<PluginInformation> {
		return available.toList()
	}





	override suspend fun <T> transaction(plugin: Plugin, block: suspend (Transaction) -> T): T {
		// TODO: Use scheduler & logging
		return pancake.database.createTransaction(block)
	}





	internal suspend fun loadPlugins() {
		for (info in getLoadOrder()) {
			createAndLoadPlugin(info)
		}
	}

	override suspend fun loadPlugin(name: String) {
		loadPlugin(findPlugin(name))
	}

	private suspend fun createAndLoadPlugin(info: PluginInformation) {
		// Create plugin
		val plugin = info.constructor(pancake)
		plugins = plugins + plugin

		// Load plugin
		loadPlugin(plugin)
	}

	override suspend fun loadPlugin(plugin: Plugin) {
		// Already enabled?
		if (isLoaded(plugin)) return

		// Log
		plugin.logger.info("Loading ${plugin.info.displayName}...")

		// Validate dependencies
		for (dependency in plugin.info.dependencies) {
			if (!findPluginByID(dependency).isLoaded) {
				throw IllegalStateException("Required dependency '$dependency' is not loaded.")
			}
		}

		// Load plugin
		isLoaded = isLoaded + plugin

		// Call plugin
		plugin.onLoad()
	}

	internal suspend fun enablePlugins() {
		for (info in getLoadOrder()) {
			enablePlugin(findPlugin(info))
		}
	}

	override suspend fun enablePlugin(name: String) {
		enablePlugin(findPlugin(name))
	}

	override suspend fun enablePlugin(plugin: Plugin) {
		// Already enabled?
		if (isEnabled(plugin)) return

		// Log
		plugin.logger.info("Enabling ${plugin.info.displayName}...")

		// Validate dependencies
		for (dependency in plugin.info.dependencies) {
			if (!findPluginByID(dependency).isEnabled) {
				throw IllegalStateException("Required dependency '$dependency' is not enabled.")
			}
		}

		// Enable plugin
		isEnabled = isEnabled + plugin

		// Call plugin
		plugin.onEnable()

		// Log
		plugin.info.apply {
			val authors = authors.joinToString(", ")
			plugin.logger.info("Done! Running $displayName, v$version by $authors.")
		}
	}

	internal suspend fun disablePlugins() {
		for (info in getLoadOrder().asReversed()) {
			disablePlugin(findPlugin(info))
		}
	}

	override suspend fun disablePlugin(name: String) {
		disablePlugin(findPlugin(name))
	}

	override suspend fun disablePlugin(plugin: Plugin) {
		// Not enabled?
		if (!isEnabled(plugin)) return

		// Log
		plugin.logger.info("Disabling ${plugin.info.displayName}...")

		// Disable plugin
		isLoaded = isLoaded - plugin
		isEnabled = isEnabled - plugin

		// Call plugin
		plugin.onDisable()
	}

	override fun isLoaded(name: String): Boolean {
		return isLoaded(findPlugin(name))
	}

	override fun isLoaded(plugin: Plugin): Boolean {
		return plugin in isLoaded
	}

	override fun isEnabled(name: String): Boolean {
		return isEnabled(findPlugin(name))
	}

	override fun isEnabled(plugin: Plugin): Boolean {
		return plugin in isEnabled
	}





	override suspend fun installPlugin(name: String) {
		installPlugin(findPlugin(name))
	}

	override suspend fun installPlugin(plugin: Plugin) {
		// Already installed?
		if (isInstalled(plugin)) return

		// Upgrade to current version
		upgradePlugin(plugin, 0..plugin.info.installationVersion)
	}

	override suspend fun uninstallPlugin(name: String) {
		uninstallPlugin(findPlugin(name))
	}

	override suspend fun uninstallPlugin(plugin: Plugin) {
		// Not installed?
		if (!isInstalled(plugin)) return

		// Downgrade to version 0
		downgradePlugin(plugin, plugin.installedVersion..0)
	}

	override suspend fun upgradePlugin(plugin: Plugin, range: IntRange) {
		// Correct step?
		if (range.step == -1) {
			downgradePlugin(plugin, range)
			return
		}
		require(range.step == 1) { "Range has an invalid step (${range.step} != 1)" }

		// Valid range?
		// TODO

		transaction(plugin) {
			// Upgrade plugin
			for (version in range.first until range.last) {
				plugin.onUpgrade(it, version, version + 1)
			}

			// Store new installation state
			// TODO
		}
	}

	override suspend fun downgradePlugin(plugin: Plugin, range: IntRange) {
		// Correct step?
		if (range.step == 1) {
			upgradePlugin(plugin, range)
			return
		}
		require(range.step == -1) { "Range has an invalid step (${range.step} != -1)" }

		// Valid range?
		// TODO

		transaction(plugin) {
			// Downgrade plugin
			for (version in range.last downTo range.first) {
				plugin.onDowngrade(it, version, version - 1)
			}

			// Store new installation state
			// TODO
		}
	}

	override fun isInstalled(name: String): Boolean {
		return isInstalled(findPlugin(name))
	}

	override fun isInstalled(plugin: Plugin): Boolean {
		return getInstalledVersion(plugin) > 0
	}

	override fun getInstalledVersion(name: String): Int {
		return getInstalledVersion(findPlugin(name))
	}

	override fun getInstalledVersion(plugin: Plugin): Int {
		TODO("Not yet implemented")
	}





	override fun <E : Event> listenSync(plugin: Plugin, event: KClass<E>, block: (E) -> Unit) {
		val listener = SyncListener(plugin, block)
		pancake.eventManager.event(event).addSyncListener(listener)
	}

	override fun <E : Event> listenAsync(plugin: Plugin, event: KClass<E>, block: suspend (E) -> Unit) {
		val listener = AsyncListener(plugin, block)
		pancake.eventManager.event(event).addAsyncListener(listener)
	}

	override fun <E : Event> callEvent(plugin: Plugin, klass: KClass<E>, event: E): Job {
		return pancake.eventManager.event(klass).callEvent(pancake.scheduler, plugin, event)
	}

	override fun <E : Event> callEventAsync(plugin: Plugin, klass: KClass<E>, event: E): Deferred<Unit> {
		return pancake.eventManager.event(klass).callEventAsync(pancake.scheduler, plugin, event)
	}

	override suspend fun <E : Event> callEventSync(plugin: Plugin, klass: KClass<E>, event: E) {
		pancake.eventManager.event(klass).callEventSync(pancake.scheduler, plugin, event)
	}

}
