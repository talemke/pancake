package net.tassia.pancake.server.http.plugin

import io.ktor.application.*
import io.ktor.features.*

fun Application.installAutoHeadResponse() {
	install(AutoHeadResponse)
}
