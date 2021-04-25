package net.tassia.pancake.plugin.cli.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.cli.CliPlugin

/**
 * This event is called when commands are about to registered.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class CliRegisterCommandsEvent(

	cli: CliPlugin,
	pancake: Pancake,

) : CliEvent(cli, pancake)
