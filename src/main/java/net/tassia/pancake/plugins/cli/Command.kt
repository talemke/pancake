package net.tassia.pancake.plugins.cli

import net.tassia.pancake.Pancake

/**
 * This is the actual command executor.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface Command {

	/**
	 * Invoked when the user executes a command.
	 *
	 * @param pancake the [Pancake] instance
	 * @param args the arguments
	 * @param toggles toggle flags
	 * @param flags value flags
	 */
	fun onCommand(pancake: Pancake, args: List<String>, toggles: Map<String, Boolean>, flags: Map<String, String>)

}
