package net.tassia.pancake.server.http.routing

class Router {

	var routes: Set<Route> = setOf(); private set

	fun addRoute(route: Route) {
		routes = routes + route
	}

}
