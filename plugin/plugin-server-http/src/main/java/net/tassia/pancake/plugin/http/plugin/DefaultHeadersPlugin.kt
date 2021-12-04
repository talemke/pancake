package net.tassia.pancake.plugin.http.plugin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

internal fun Application.installDefaultHeaders() {
	install(DefaultHeaders) {
		header(HttpHeaders.Server, "Ktor@Netty/1.6.4")
	}
}
