package net.tassia.pancake.server.http.plugin

import io.ktor.application.*
import io.ktor.routing.*
import net.tassia.pancake.server.http.routing.Route
import net.tassia.pancake.server.http.routing.Router

fun Application.installRouting(router: Router) {
	install(Routing) {
		for (route in router.routes) {
			registerRoute(route)
		}
	}
}

private fun Routing.registerRoute(route: Route) {
	TODO()
}
