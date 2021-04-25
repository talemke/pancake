package net.tassia.pancake.plugin.cli.event

import net.tassia.pancake.event.PluginEvent
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
	val cli: CliPlugin,

) : PluginEvent(cli)
