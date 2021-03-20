package net.tassia.pancake.plugin.core

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo

/**
 * The core plugin for Pancake. Adds the base functions.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class CorePlugin(override val pancake: Pancake) : Plugin(pancake) {

	override val info: PluginInfo = Info



	override fun onEnable() {
		// TODO
	}



	override fun onDisable() {
		// TODO
	}



	companion object {

		/**
		 * The version information for the core plugin.
		 */
		val Version = Pancake.VERSION

		/**
		 * The plugin information for the core plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:Core",
			name = "Core",
			description = "Core systems for Pancake.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::CorePlugin
		)

	}

}
