package net.tassia.pancake.plugin.http.routing

import io.ktor.application.*
import io.ktor.response.*
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.Router

internal fun Router.registerGenericRoutes(plugin: Plugin) {

	get(plugin, "/api/v1/version") {
		call.respond(Pancake.VERSION)
	}

}
