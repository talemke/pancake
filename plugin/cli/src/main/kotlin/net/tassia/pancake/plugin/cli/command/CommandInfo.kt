package net.tassia.pancake.plugin.cli.command

/**
 * Holds information about a command.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class CommandInfo(

	/**
	 * The name of this command.
	 */
	val name: String,

	/**
	 * A brief description for this command.
	 */
	val description: String,

	/**
	 * A set of aliases of this command.
	 */
	val aliases: Set<String> = setOf(),

	/**
	 * A set of usage-description pairs of this command.
	 */
	val usages: Map<String, String> = mapOf()

)
