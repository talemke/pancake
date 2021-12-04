package net.tassia.pancake.plugin.http.plugin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

internal fun Application.installContentNegotiation() {
	install(ContentNegotiation) {
		json()
	}
}
