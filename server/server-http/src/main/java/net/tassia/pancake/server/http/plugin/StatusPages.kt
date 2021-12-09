package net.tassia.pancake.server.http.plugin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import net.tassia.pancake.server.http.data.StatusResponse
import net.tassia.pancake.server.http.transaction.InterruptTransactionException

fun Application.installStatusPages() {
	install(StatusPages) {

		exception<InterruptTransactionException> {
			// Do nothing.
		}

		exception<Throwable> { cause ->
			val data = StatusResponse.InternalServerError
			call.respond(HttpStatusCode.InternalServerError, data)
		}

	}
}
