package net.tassia.pancake.server.http.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.util.pipeline.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.impl.PrimitiveRoute

class Router {

	var routes: Set<Route> = setOf(); private set

	fun addRoute(route: Route) {
		routes = routes + route
	}





	@RouteDSL
	fun get(plugin: Plugin, path: String, block: PipelineInterceptor<Unit, ApplicationCall>): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Get, block).also(this::addRoute)
	}

	@RouteDSL
	fun post(plugin: Plugin, path: String, block: PipelineInterceptor<Unit, ApplicationCall>): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Post, block).also(this::addRoute)
	}

	@RouteDSL
	fun put(plugin: Plugin, path: String, block: PipelineInterceptor<Unit, ApplicationCall>): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Put, block).also(this::addRoute)
	}

	@RouteDSL
	fun delete(plugin: Plugin, path: String, block: PipelineInterceptor<Unit, ApplicationCall>): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Delete, block).also(this::addRoute)
	}

}
