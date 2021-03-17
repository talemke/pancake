package net.tassia.pancake.plugins.cli.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugins.cli.PancakeCLI

/**
 * This event is called when commands are about to registered.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class CliRegisterCommandsEvent(

	override val pancake: Pancake,
	override val cli: PancakeCLI,

) : CliEvent(pancake, cli)
