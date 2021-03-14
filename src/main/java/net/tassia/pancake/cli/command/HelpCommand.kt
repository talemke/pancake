package net.tassia.pancake.cli.command

import net.tassia.pancake.cli.Command
import net.tassia.pancake.cli.CommandInfo

/**
 * The 'HELP' command. Shows helpful information.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object HelpCommand {

	/**
	 * The command information.
	 */
	val Info = CommandInfo(
		name = "Help",
		description = "Shows helpful information.",
		aliases = setOf("?"),
		usages = mapOf(
			Pair("help", "Shows helpful information.")
		)
	)

	/**
	 * The command executor.
	 */
	val Executor = Command { pancake, args, toggles, flags ->
		TODO()
	}

}
