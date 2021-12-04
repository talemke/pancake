package net.tassia.pancake.plugin.http.event

import io.ktor.routing.*
import net.tassia.pancake.event.Event

class RegisterRoutesEvent(val router: Routing) : Event()
