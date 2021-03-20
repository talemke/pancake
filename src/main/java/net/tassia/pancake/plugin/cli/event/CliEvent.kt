package net.tassia.pancake.plugin.cli.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.event.PancakeEvent
import net.tassia.pancake.plugin.cli.CliPlugin

/**
 * All CLI related events extend this class.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class CliEvent(

	/**
	 * The current [CliPlugin].
	 */
	open val cli: CliPlugin,

	override val pancake: Pancake,

) : PancakeEvent(pancake)
