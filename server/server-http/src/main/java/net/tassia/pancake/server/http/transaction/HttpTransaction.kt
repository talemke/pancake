package net.tassia.pancake.server.http.transaction

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.data.StatusResponse

class HttpTransaction(val plugin: Plugin, @PublishedApi internal val call: ApplicationCall) {

	suspend inline fun <reified T : Any> receiveJSON(): T {
		return call.receive()
	}



	suspend fun respondJSON(data: Any, status: HttpStatusCode) {
		call.respond(status, data)
	}

	suspend fun respondStatus(status: HttpStatusCode) {
		// Generate data
		val data = StatusResponse[status.value]
			?: StatusResponse(status.value, status.description, "No description provided.")

		// Respond
		respondJSON(data, status)
	}



	suspend fun authenticate(): Any {
		// Valid authentication?
		val authentication = authenticateOrNull()
		if (authentication != null) return authentication

		// Respond & suppress execution
		respondStatus(HttpStatusCode.Forbidden)
		throw InterruptTransactionException()
	}

	suspend fun authenticateOrNull(): Any? {
		TODO()
	}

}
