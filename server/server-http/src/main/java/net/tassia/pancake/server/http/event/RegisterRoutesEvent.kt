package net.tassia.pancake.server.http.event

import net.tassia.pancake.event.Event
import net.tassia.pancake.server.http.routing.Router

class RegisterRoutesEvent(val router: Router) : Event()
