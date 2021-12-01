package net.tassia.pancake.plugin

import net.tassia.Version

abstract class PluginInformation {

	abstract val identifier: String
	abstract val name: String
	abstract val displayName: String
	abstract val authors: Set<String>
	abstract val description: String
	abstract val website: String



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
	 * Whether the plugin should be globally enabled (i.e. on all nodes)
	 * or if this is a node-specific plugin.
	 */
	abstract val isGlobal: Boolean

}
