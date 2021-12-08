package net.tassia.pancake.entity

sealed interface Option<T : Option<T>> : DatabaseEntity<T> {

	val option: String
	val description: String?
	val defaultValue: String
	val currentValue: String?

	val isSystemOption: Boolean

	val effectiveValue: String

}
