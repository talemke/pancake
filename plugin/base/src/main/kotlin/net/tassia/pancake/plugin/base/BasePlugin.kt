package net.tassia.pancake.plugin.base

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugin.base.listener.mail.CoreIncomingMailListener
import net.tassia.pancake.plugin.base.listener.mail.CoreMailRouteListener
import net.tassia.pancake.plugin.base.listener.mail.CoreMailRoutedListener
import net.tassia.pancake.util.Version
import net.tassia.pancake.util.version.VersionType

/**
 * The core plugin for Pancake. Adds the base functions.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class BasePlugin(pancake: Pancake) : Plugin(pancake) {

	override val info: PluginInfo = Info



	override fun onLoad() {
		// TODO
	}

	override fun onEnable() {
		// Register listeners
		pancake.events.register(CoreIncomingMailListener(this))
		pancake.events.register(CoreMailRoutedListener(this))
		pancake.events.register(CoreMailRouteListener(this))
	}

	override fun onDisable() {
		// TODO
	}



	companion object {

		/**
		 * The plugin information for the core plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:Core",
			name = "Core",
			description = "Core systems for Pancake.",
			authors = setOf("Tassilo"),
			version = Version(1, 0, 0, 1, "", "main", VersionType.RELEASE),
			constructor = ::BasePlugin,
		)

	}

}
