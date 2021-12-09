package net.tassia.pancake.plugin.http.routing

import io.ktor.http.*
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.Router

internal fun Router.registerLegalRoutes(plugin: Plugin) {

	get(plugin, "/api/v1/legal/cookies") {
		// TODO
		respondText("Lorem ipsum dies das", HttpStatusCode.OK)
	}

	get(plugin, "/api/v1/legal/privacy") {
		// TODO
		respondText("Lorem ipsum dies das", HttpStatusCode.OK)
	}

	get(plugin, "/api/v1/legal/tos") {
		// TODO
		respondText("Lorem ipsum dies das", HttpStatusCode.OK)
	}

	get(plugin, "/api/v1/version") {
		respondJSON(Pancake.VERSION, HttpStatusCode.OK)
	}

}
