package net.tassia.pancake.plugin.core

import net.tassia.event.EventManager
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugin.core.event.mail.IncomingMailEvent
import net.tassia.pancake.plugin.core.event.mail.MailRouteEvent
import net.tassia.pancake.plugin.core.event.mail.MailRoutedEvent
import net.tassia.pancake.plugin.core.listener.mail.CoreIncomingMailListener
import net.tassia.pancake.plugin.core.listener.mail.CoreMailRouteListener
import net.tassia.pancake.plugin.core.listener.mail.CoreMailRoutedListener

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



	override fun onLoad() {
		// TODO
	}

	override fun onEnable() {
		// Register listeners
		pancake.events.registerListener(CoreIncomingMailListener(this))
		pancake.events.registerListener(CoreMailRoutedListener(this))
		pancake.events.registerListener(CoreMailRouteListener(this))
	}

	override fun onDisable() {
		// TODO
	}



	companion object {

		/**
		 * The version information for the core plugin.
		 */
		private val Version = Pancake.VERSION

		/**
		 * Registers all events for this plugin.
		 */
		private val RegisterEvents = { events: EventManager ->
			events.registerEvent<IncomingMailEvent>()
			events.registerEvent<MailRoutedEvent>()
			events.registerEvent<MailRouteEvent>()
		}

		/**
		 * The plugin information for the core plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:Core",
			name = "Core",
			description = "Core systems for Pancake.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::CorePlugin,
			events = RegisterEvents,
		)

	}

}
