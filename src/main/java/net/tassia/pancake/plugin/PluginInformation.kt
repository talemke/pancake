package net.tassia.pancake.plugin

import net.tassia.Version
import net.tassia.pancake.Pancake

/**
 * Contains information about a [Plugin].
 *
 * @since Pancake 1.0
 */
abstract class PluginInformation {

	/**
	 * A (unique) identifier for this plugin. Usually in the format of 'groupID:pluginID'.
	 */
	abstract val identifier: String

	/**
	 * The name of this plugin.
	 */
	abstract val name: String

	/**
	 * The display name of this plugin.
	 */
	abstract val displayName: String

	/**
	 * A set of authors, who worked on this plugin.
	 */
	abstract val authors: Set<String>

	/**
	 * A short description of this plugin.
	 */
	abstract val description: String

	/**
	 * The website of this plugin.
	 */
	abstract val website: String



	/**
	 * Dependencies of this plugin. If a dependency is available, it will be loaded before
	 * this plugin. If a dependency is not available, this plugin will not be loaded.
	 *
	 * The strings in this set are the [PluginInformation.identifier].
	 */
	abstract val dependencies: Set<String>

	/**
	 * Soft-dependencies of this plugin. If a soft-dependency is available,
	 * it will be loaded before this plugin. If a soft-dependency is not
	 * available, it will silently be ignored.
	 *
	 * The strings in this set are the [PluginInformation.identifier].
	 */
	abstract val softDependencies: Set<String>



	/**
	 * The current version of the plugin.
	 */
	abstract val version: Version

	/**
	 * The current installation version of this plugin. Incrementing this will cause
	 * the [Plugin.onUpgrade] method to be invoked, next time the plugin is loaded.
	 */
	abstract val installationVersion: Int

	/**
	 * The required version of Pancake for this plugin.
	 */
	abstract val apiVersion: Version



	/**
	 * Whether the plugin should be globally enabled (i.e. on all nodes)
	 * or if this is a node-specific plugin.
	 */
	abstract val isGlobal: Boolean



	/**
	 * The plugin constructor.
	 */
	abstract val constructor: (Pancake) -> Plugin

}
