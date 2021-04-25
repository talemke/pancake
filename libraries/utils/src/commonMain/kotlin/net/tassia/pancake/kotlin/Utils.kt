package net.tassia.pancake.kotlin

val Boolean.enable: String
	get() = either(this, "enable", "disable")
