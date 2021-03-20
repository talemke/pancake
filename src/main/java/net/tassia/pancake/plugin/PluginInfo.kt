package net.tassia.pancake.plugin

import net.tassia.pancake.Pancake
import net.tassia.pancake.util.version.Version

/**
 * Holds information about a specific plugin.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class PluginInfo(

	/**
	 * A unique identifier for this plugin. By convention, follows the following format:
	 *
	 * `TLD.AUTHOR:PLUGIN`
	 *
	 * For example:
	 *
	 * `net.tassia:CLI`
	 */
	val id: String,

	/**
	 * The name of this plugin.
	 */
	val name: String,

	/**
	 * The description of this plugin.
	 */
	val description: String,

	/**
	 * The authors of this plugin.
	 */
	val authors: Set<String>,

	/**
	 * The current version of this plugin.
	 */
	val version: Version,

	/**
	 * The constructor for this plugin.
	 */
	val constructor: (Pancake) -> Plugin,

	)
