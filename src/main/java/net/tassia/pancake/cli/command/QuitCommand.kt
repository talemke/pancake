package net.tassia.pancake.cli.command

import net.tassia.pancake.cli.CLI
import net.tassia.pancake.cli.Command
import net.tassia.pancake.cli.CommandInfo

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
	val Executor = Command { pancake, _, _, _ ->
		CLI.print("Good bye!")
		pancake.exit(0)
	}

}
