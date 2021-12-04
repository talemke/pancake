package net.tassia.pancake.plugin.http.plugin

import io.ktor.application.*
import io.ktor.routing.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.http.event.RegisterRoutesEvent

internal fun Application.installRouting(plugin: Plugin) {
	install(Routing) {
		val event = RegisterRoutesEvent(this)
		plugin.callEvent(event)
	}
}
