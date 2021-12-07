package net.tassia.pancake.server.http.transaction

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import net.tassia.pancake.plugin.Plugin

class HttpTransaction(val plugin: Plugin, @PublishedApi internal val call: ApplicationCall) {

	suspend inline fun <reified T : Any> receiveJSON(): T {
		return call.receive()
	}



	suspend fun respondJSON(data: Any, status: HttpStatusCode) {
		call.respond(status, data)
	}

}
