package net.tassia.pancake.plugin.cli.command.basic

import net.tassia.pancake.plugin.cli.command.Command
import net.tassia.pancake.plugin.cli.command.CommandInfo

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
	val Executor = Command { pancake, args, flags ->
		TODO()
	}

}
