package net.tassia.pancake.plugin

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.core.CorePlugin
import net.tassia.pancake.plugins.cli.PancakeCLI

/**
 * Used to manage plugins.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PluginManager(val pancake: Pancake) {

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
		pancake.logger.info("Loading plugins...")
		var count = 0

		// TODO

		registerPlugin(CorePlugin.Info)
		registerPlugin(PancakeCLI.Info)

		pancake.logger.info("Loaded $count plugins.")
	}

	private fun registerPlugin(info: PluginInfo) {
		plugins[info] = info.constructor(pancake)
	}



	fun enablePlugins() {
		pancake.logger.info("Enabling plugins...")
		plugins.values.forEach { enablePlugin(it) }
		pancake.logger.info("Enabled ${enabled.size} plugins.")
	}

	private fun enablePlugin(plugin: Plugin) {
		plugin.info.also {
			pancake.logger.info("- Enabling ${it.name}, version ${it.version.toDisplayString()}")
		}
		plugin.events.forEach { pancake.events.registerEvent(it) }
		plugin.onEnable()
		enabled.add(plugin.info)
	}



	fun disablePlugins() {
		pancake.logger.info("Disabling plugins...")
		val count = enabled.size
		plugins.values.forEach { disablePlugin(it) }
		pancake.logger.info("Disabled $count plugins.")
	}

	private fun disablePlugin(plugin: Plugin) {
		pancake.logger.info("- Disabling ${plugin.info.name}")
		plugin.onDisable()
		enabled.remove(plugin.info)
	}

}
