package net.tassia.pancake.plugin.http.plugin

import io.ktor.application.*
import io.ktor.routing.*
import net.tassia.pancake.server.http.routing.Route
import net.tassia.pancake.server.http.routing.Router

internal fun Application.installRouting(router: Router) {
	install(Routing) {
		for (route in router.routes) {
			registerRoute(route)
		}
	}
}

private fun Routing.registerRoute(route: Route) {
	TODO()
}
