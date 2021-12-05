package net.tassia.pancake.entity

interface GlobalOption : DatabaseEntity<GlobalOption> {

	val option: String
	val description: String?
	val defaultValue: String?
	val currentValue: String

	val effectiveValue: String

}
