package net.tassia.pancake.server.http.routing

import io.ktor.application.*
import io.ktor.http.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.impl.PrimitiveRoute
import net.tassia.pancake.server.http.transaction.HttpTransaction

class Router {

	var routes: Set<Route> = setOf(); private set

	fun addRoute(route: Route) {
		routes = routes + route
	}





	@RouteDSL
	fun get(plugin: Plugin, path: String, block: suspend HttpTransaction.() -> Unit): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Get) {
			HttpTransaction(plugin, call).block()
		}.also(this::addRoute)
	}

	@RouteDSL
	fun post(plugin: Plugin, path: String, block: suspend HttpTransaction.() -> Unit): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Post) {
			HttpTransaction(plugin, call).block()
		}.also(this::addRoute)
	}

	@RouteDSL
	fun put(plugin: Plugin, path: String, block: suspend HttpTransaction.() -> Unit): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Put) {
			HttpTransaction(plugin, call).block()
		}.also(this::addRoute)
	}

	@RouteDSL
	fun delete(plugin: Plugin, path: String, block: suspend HttpTransaction.() -> Unit): Route {
		return PrimitiveRoute(plugin, path, HttpMethod.Delete) {
			HttpTransaction(plugin, call).block()
		}.also(this::addRoute)
	}

}
