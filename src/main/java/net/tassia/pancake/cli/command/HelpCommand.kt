package net.tassia.pancake.cli.command

import net.tassia.pancake.cli.Command
import net.tassia.pancake.cli.CommandInfo

object HelpCommand {

	val Info = CommandInfo(
		name = "Help",
		description = "Shows helpful information.",
		aliases = setOf("?"),
		usages = mapOf(
			Pair("help", "Shows helpful information.")
		)
	)

	val Executor = Command { pancake, args, toggles, flags ->
		TODO()
	}

}
