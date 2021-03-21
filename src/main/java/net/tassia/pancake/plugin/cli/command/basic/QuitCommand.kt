package net.tassia.pancake.plugin.cli.command.basic

import net.tassia.pancake.plugin.cli.CLI
import net.tassia.pancake.plugin.cli.command.Command
import net.tassia.pancake.plugin.cli.command.CommandInfo

/**
 * The 'QUIT' command. Exits the application.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object QuitCommand {

	/**
	 * The command information.
	 */
	val Info = CommandInfo(
		name = "Quit",
		description = "Exits the application.",
		aliases = setOf("end", "exit"),
		usages = mapOf(
			Pair("quit", "Exits the application.")
		)
	)

	/**
	 * The command executor.
	 */
	val Executor = Command { pancake, _, _ ->
		CLI.print("Good bye!")
		pancake.exit(0)
	}

}
