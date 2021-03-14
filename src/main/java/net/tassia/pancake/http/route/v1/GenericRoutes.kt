package net.tassia.pancake.http.route.v1

import net.tassia.pancake.http.RouteRegistrar
import net.tassia.pancake.http.data.v1.PingResponse

/**
 * Registers all generic routes of the Version 1 API.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun RouteRegistrar.registerGenericRoutes() {

	// GET /v1/ping
	//
	// Request: N/A
	// Response: Ping
	//
	// Used to check if the API is alive.
	get("/v1/ping") {
		return@get PingResponse()
	}

}
