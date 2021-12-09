package net.tassia.pancake.server.http.routing

import io.ktor.application.*
import io.ktor.http.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.impl.PrimitiveRoute
import net.tassia.pancake.server.http.transaction.HttpTransaction
import net.tassia.pancake.server.http.transaction.InterruptTransactionException

class Router {

	var routes: Set<Route> = setOf(); private set

	fun addRoute(route: Route) {
		routes = routes + route
	}

	private fun addRoute(plugin: Plugin, path: String, method: HttpMethod, block: RouteExecutor): Route {
		return PrimitiveRoute(plugin, path, method) {
			try {
				HttpTransaction(plugin, call).block()
			} catch (ignored: InterruptTransactionException) {
				// Do nothing.
			}
		}.also(this::addRoute)
	}





	@RouteDSL
	fun get(plugin: Plugin, path: String, block: RouteExecutor): Route {
		return addRoute(plugin, path, HttpMethod.Get, block)
	}

	@RouteDSL
	fun post(plugin: Plugin, path: String, block: RouteExecutor): Route {
		return addRoute(plugin, path, HttpMethod.Post, block)
	}

	@RouteDSL
	fun put(plugin: Plugin, path: String, block: RouteExecutor): Route {
		return addRoute(plugin, path, HttpMethod.Put, block)
	}

	@RouteDSL
	fun delete(plugin: Plugin, path: String, block: RouteExecutor): Route {
		return addRoute(plugin, path, HttpMethod.Delete, block)
	}

}
