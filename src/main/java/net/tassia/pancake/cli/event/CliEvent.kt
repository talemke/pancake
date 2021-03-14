package net.tassia.pancake.cli.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.cli.PancakeCLI
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
	val cli: PancakeCLI = pancake.cli,

) : PancakeEvent(pancake)
