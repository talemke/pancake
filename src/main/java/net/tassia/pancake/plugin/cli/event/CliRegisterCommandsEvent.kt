package net.tassia.pancake.plugin.cli.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.cli.CliPlugin

/**
 * This event is called when commands are about to registered.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class CliRegisterCommandsEvent(

	override val cli: CliPlugin,
	override val pancake: Pancake,

) : CliEvent(cli, pancake)
