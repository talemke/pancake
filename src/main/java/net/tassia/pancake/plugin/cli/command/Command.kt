package net.tassia.pancake.plugin.cli.command

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
	 * @param flags the flags
	 */
	fun onCommand(pancake: Pancake, args: List<String>, flags: Map<String, String>)

}
