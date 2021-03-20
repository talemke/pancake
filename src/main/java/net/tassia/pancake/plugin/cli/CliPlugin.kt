package net.tassia.pancake.plugin.cli

import net.tassia.event.Event
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugin.cli.command.Command
import net.tassia.pancake.plugin.cli.command.CommandInfo
import net.tassia.pancake.plugin.cli.event.CliEvent
import net.tassia.pancake.plugin.cli.event.CliRegisterCommandsEvent
import net.tassia.pancake.plugin.core.listener.mail.CoreIncomingMailListener
import net.tassia.pancake.plugin.core.listener.mail.CoreMailRouteListener
import net.tassia.pancake.plugin.core.listener.mail.CoreMailRoutedListener
import kotlin.reflect.KClass

/**
 * The base class for interacting with Pancake's command-line interface.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class CliPlugin(override val pancake: Pancake) : Plugin(pancake) {

	override val info: PluginInfo = Info

	override val events: Set<KClass<out Event>> = setOf(
		CliEvent::class, CliRegisterCommandsEvent::class
	)



	/**
	 * A map of all registered commands.
	 */
	private val commands: MutableMap<CommandInfo, Command> = mutableMapOf()

	/**
	 * The actual listener that listens for commands by the user.
	 */
	private val listener: CommandListener = CommandListener(pancake) { search ->
		commands.entries.find {
			it.key.name.equals(search, true)
		}?.value
	}

	/**
	 * Holds a reference to the thread listening for new user input.
	 */
	private val thread: Thread = Thread(listener, "PancakeCLI")



	override fun onEnable() {
		// Register listeners
		pancake.events.registerListener(CoreIncomingMailListener)
		pancake.events.registerListener(CoreMailRoutedListener)
		pancake.events.registerListener(CoreMailRouteListener)

		// Register commands
		// TODO

		// Start thread
		if (!thread.isAlive) {
			thread.start()
		}
	}



	override fun onDisable() {
		// Stop thread
		listener.listening = false
		if (thread.isAlive) {
			thread.interrupt()
		}
	}



	/**
	 * Returns set of all registered commands.
	 *
	 * @return commands
	 */
	fun commands(): Set<CommandInfo> = commands.keys

	/**
	 * Adds/Registers a new command.
	 *
	 * @param info command information
	 * @param command the command
	 */
	fun add(info: CommandInfo, command: Command) = commands.set(info, command)

	/**
	 * Removes/Unregisters an existing command.
	 *
	 * @param info the command
	 */
	fun remove(info: CommandInfo) = commands.remove(info)



	companion object {

		/**
		 * The version information for the core plugin.
		 */
		val Version = Pancake.VERSION

		/**
		 * The plugin information for the core plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:CLI",
			name = "CLI",
			description = "The command-line interface for Pancake.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::CliPlugin
		)

	}

}
