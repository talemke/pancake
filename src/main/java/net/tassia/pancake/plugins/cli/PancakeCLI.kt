package net.tassia.pancake.plugins.cli

import net.tassia.pancake.Pancake
import net.tassia.pancake.Version
import net.tassia.pancake.VersionType
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugins.cli.event.CliEvent
import net.tassia.pancake.plugins.cli.event.CliRegisterCommandsEvent
import net.tassia.pancake.plugins.cli.listener.CoreCliRegisterCommandsListener

/**
 * The base class for interacting with Pancake's command-line interface.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PancakeCLI(override val pancake: Pancake) : Plugin(pancake) {

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



	override fun onEnable() {
		// Register CLI events
		pancake.events.registerEvent<CliEvent>()
		pancake.events.registerEvent<CliRegisterCommandsEvent>()

		// Register commands
		pancake.events.registerListener(CoreCliRegisterCommandsListener)
		pancake.events.callEvent(CliRegisterCommandsEvent(pancake))

		// Start thread
		if (!thread.isAlive) {
			thread.start()
		}
	}

	override fun onDisable() {
		listener.listening = false
		if (thread.isAlive) {
			thread.interrupt()
		}
	}

	override fun onInstall() = Unit
	override fun onUninstall() = Unit



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
		 * The version information for the CLI plugin.
		 */
		val Version = Version(1, 0, 0, 1, "dbd3766fc98765487e213418f5c600e027c8957a", branch = "main", type = VersionType.SNAPSHOT)

		/**
		 * The plugin information for the CLI plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:CLI",
			name = "CLI",
			description = "The command-line interface for Pancake.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::PancakeCLI
		)

	}

}
