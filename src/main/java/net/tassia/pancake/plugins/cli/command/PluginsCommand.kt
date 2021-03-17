package net.tassia.pancake.plugins.cli.command

import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugins.cli.CLI
import net.tassia.pancake.plugins.cli.Command
import net.tassia.pancake.plugins.cli.CommandInfo

/**
 * The 'PLUGINS' command. Shows all available plugins.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object PluginsCommand {

	/**
	 * The command information.
	 */
	val Info = CommandInfo(
		name = "Plugins",
		description = "Shows all available plugins.",
		aliases = setOf("plugin", "pl"),
		usages = mapOf(
			Pair("plugins", "Shows all available plugins."),
			Pair("plugin <plugin>", "Shows information about a specific plugin.")
		)
	)

	/**
	 * The command executor.
	 */
	val Executor = Command { pancake, args, _, _ ->
		when {
			args.isEmpty() -> {
				val pl = pancake.plugins.plugins().values
				CLI.print("Listing all ${pl.size} available plugins:")
				CLI.print()
				pl.forEach(this::printPluginListLine)
			}
			args.size == 1 -> {

			}
			else -> {
				CLI.failure("Incorrect usage.")
			}
		}
	}

	private fun printPluginListLine(plugin: Plugin) {
		val msg = plugin.info.run {
			"$name - Version ${version.toDisplayString()} - by ${authors.joinToString()}"
		}
		if (plugin.isEnabled()) {
			CLI.success(msg)
		} else {
			val installed = if (plugin.isInstalled()) "Installed" else "Not Installed"
			CLI.failure("$msg ($installed)")
		}
	}

}
