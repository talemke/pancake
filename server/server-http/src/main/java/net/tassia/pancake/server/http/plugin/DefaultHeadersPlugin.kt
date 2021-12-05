package net.tassia.pancake.server.http.plugin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun Application.installDefaultHeaders() {
	install(DefaultHeaders) {
		header(HttpHeaders.Server, "Ktor@Netty/1.6.4")
	}
}
