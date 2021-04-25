package net.tassia.pancake.util

import kotlin.system.exitProcess

actual object System {

	actual fun timestamp(): Long = java.lang.System.currentTimeMillis()

	actual fun exit(status: Int) {
		exitProcess(status)
	}

}
