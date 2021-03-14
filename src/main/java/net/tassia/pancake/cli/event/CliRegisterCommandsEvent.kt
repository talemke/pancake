package net.tassia.pancake.cli.event

import net.tassia.pancake.Pancake

/**
 * This event is called when commands are about to registered.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class CliRegisterCommandsEvent(

	override val pancake: Pancake

) : CliEvent(pancake)
