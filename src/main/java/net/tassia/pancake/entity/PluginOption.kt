package net.tassia.pancake.entity

interface PluginOption : DatabaseEntity<PluginOption> {

	val pluginID: String
	val option: String
	val description: String?
	val defaultValue: String
	val currentValue: String?

	val effectiveValue: String

}
