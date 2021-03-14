package net.tassia.pancake.cli

import net.tassia.pancake.Pancake
import net.tassia.pancake.cli.event.CliEvent
import net.tassia.pancake.cli.event.CliRegisterCommandsEvent

/**
 * The base class for interacting with Pancake's command-line interface.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PancakeCLI(private val pancake: Pancake) {

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



	init {
		// Register CLI events
		pancake.events.registerEvent<CliEvent>()
		pancake.events.registerEvent<CliRegisterCommandsEvent>()
	}



	/**
	 * Starts the CLI and the underlying thread.
	 */
	fun start() {
		// Register commands
		pancake.events.callEvent(CliRegisterCommandsEvent(pancake))

		// Start thread
		if (!thread.isAlive) {
			thread.start()
		}
	}

	/**
	 * Stops the CLI by interrupting the underlying thread.
	 */
	fun stop() {
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

}
