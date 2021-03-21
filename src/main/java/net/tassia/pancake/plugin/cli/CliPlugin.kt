package net.tassia.pancake.plugin.cli

import net.tassia.event.EventManager
import net.tassia.pancake.Pancake
import net.tassia.pancake.event.PancakePostInitEvent
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugin.cli.command.Command
import net.tassia.pancake.plugin.cli.command.CommandInfo
import net.tassia.pancake.plugin.cli.command.basic.HelpCommand
import net.tassia.pancake.plugin.cli.command.basic.PluginsCommand
import net.tassia.pancake.plugin.cli.command.basic.QuitCommand
import net.tassia.pancake.plugin.cli.event.CliEvent
import net.tassia.pancake.plugin.cli.event.CliRegisterCommandsEvent

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



	override fun onLoad() {
		// TODO
	}

	override fun onEnable() {
		// Register default commands
		add(HelpCommand.Info, HelpCommand.Executor)
		add(PluginsCommand.Info, PluginsCommand.Executor)
		add(QuitCommand.Info, QuitCommand.Executor)

		// Register external commands
		pancake.events.callEvent(CliRegisterCommandsEvent(this, pancake))

		// Start thread after Pancake
		pancake.events.registerListener { _: PancakePostInitEvent ->
			if (!thread.isAlive) {
				thread.start()
			}
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
		private val Version = Pancake.VERSION

		/**
		 * Registers all events for this plugin.
		 */
		private val RegisterEvents = { events: EventManager ->
			events.registerEvent<CliEvent>()
			events.registerEvent<CliRegisterCommandsEvent>()
		}

		/**
		 * The plugin information for the core plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:CLI",
			name = "CLI",
			description = "The command-line interface for Pancake.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::CliPlugin,
			events = RegisterEvents,
		)

	}

}
