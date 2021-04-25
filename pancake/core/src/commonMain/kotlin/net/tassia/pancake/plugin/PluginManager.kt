package net.tassia.pancake.plugin

import net.tassia.pancake.Pancake
import net.tassia.pancake.Logger

/**
 * Used to manage plugins.
 *
 * @since PancakePlugins 1.0
 * @author Tassilo
 */
class PluginManager(val core: Pancake) {

	/**
	 * This internal map contains all registered plugins.
	 */
	private val plugins: MutableMap<PluginInfo, Plugin> = mutableMapOf()

	/**
	 * This internal set contains all enabled plugins.
	 */
	private val enabled: MutableSet<PluginInfo> = mutableSetOf()

	/**
	 * This internal contains all installed plugins.
	 */
	private val installed: MutableSet<PluginInfo> = mutableSetOf()



	fun plugins(): Map<PluginInfo, Plugin> = plugins

	fun plugin(info: PluginInfo): Plugin? = plugins[info]

	fun plugin(id: String): Plugin? = plugins.values.find { it.info.id == id }



	fun isPluginEnabled(info: PluginInfo): Boolean = enabled.contains(info)

	fun isPluginInstalled(info: PluginInfo): Boolean = installed.contains(info)



	fun locatePlugins() {
		Logger.info("Loading plugins...")
		var count = 0

		// TODO

		Logger.info("Loaded $count plugins.")
	}

	private fun registerPlugin(info: PluginInfo) {
		plugins[info] = info.constructor(core)
	}



	fun loadPlugins() {
		Logger.info("Loading plugins...")
		plugins.values.forEach { loadPlugin(it) }
	}

	private fun loadPlugin(plugin: Plugin) {
		plugin.info.also {
			Logger.info("- Loading plugin '${it.name}'...")
		}
		plugin.info.events(core.events)
		plugin.onLoad()
	}



	fun enablePlugins() {
		Logger.info("Enabling plugins...")
		plugins.values.forEach { enablePlugin(it) }
		Logger.info("Enabled ${enabled.size} plugins.")
	}

	private fun enablePlugin(plugin: Plugin) {
		Logger.info("- Enabling plugin '${plugin.info.name}'...")
		plugin.onEnable()
		enabled.add(plugin.info)
	}



	fun disablePlugins() {
		Logger.info("Disabling plugins...")
		val count = enabled.size
		plugins.values.forEach { disablePlugin(it) }
		Logger.info("Disabled all $count plugins.")
	}

	private fun disablePlugin(plugin: Plugin) {
		Logger.info("- Disabling plugin '${plugin.info.name}'...")
		plugin.onDisable()
		enabled.remove(plugin.info)
	}

}
