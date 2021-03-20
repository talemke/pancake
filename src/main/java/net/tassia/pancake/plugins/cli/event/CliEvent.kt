package net.tassia.pancake.plugins.cli.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.plugins.cli.PancakeCLI
import net.tassia.pancake.event.PancakeEvent

/**
 * All CLI related events extend this class.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class CliEvent(

	override val pancake: Pancake,

	/**
	 * The current [PancakeCLI].
	 */
	open val cli: PancakeCLI,

) : PancakeEvent(pancake)
