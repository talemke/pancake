package net.tassia.pancake.plugin.http.routing

import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.Router

internal fun Router.installAuthenticationRoutes(plugin: Plugin) {

	post(plugin, "/api/v1/auth/login") {
		TODO()
	}

	post(plugin, "/api/v1/auth/logout") {
		TODO()
	}

	get(plugin, "/api/v1/auth/self") {
		TODO()
	}

}
