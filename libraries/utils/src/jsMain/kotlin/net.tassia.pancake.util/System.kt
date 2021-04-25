package net.tassia.pancake.util

import kotlinx.browser.window
import kotlin.js.Date

actual object System {

	actual fun timestamp(): Long = Date.now().toLong()

	actual fun exit(status: Int) {
		window.close()
	}

}
