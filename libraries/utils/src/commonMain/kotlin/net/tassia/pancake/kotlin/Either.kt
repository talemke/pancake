package net.tassia.pancake.kotlin

fun <T> either(test: Boolean, onTrue: T, onFalse: T): T {
	return if (test) onTrue else onFalse
}

fun <T> either(test: Boolean, onTrue: () -> T, onFalse: () -> T): T {
	return if (test) onTrue() else onFalse()
}

fun <T> either(test: Boolean, onTrue: () -> T, onFalse: T): T {
	return if (test) onTrue() else onFalse
}

fun <T> either(test: Boolean, onTrue: T, onFalse: () -> T): T {
	return if (test) onTrue else onFalse()
}
