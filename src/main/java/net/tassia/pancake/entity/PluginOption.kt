package net.tassia.pancake.entity

import java.util.*

interface PluginOption : DatabaseEntity<PluginOption> {

	val pluginID: UUID
	val option: String
	val description: String?
	val defaultValue: String?
	val currentValue: String

	val effectiveValue: String

}
