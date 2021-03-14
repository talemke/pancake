package net.tassia.pancake.http.route.v1

import net.tassia.pancake.http.event.HttpRegisterRoutesEvent

/**
 * Registers all routes of the Version 1 API. This function acts as an
 * event listener, thus it takes an [HttpRegisterRoutesEvent] as argument.
 *
 * @param event the [HttpRegisterRoutesEvent]
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun registerV1(event: HttpRegisterRoutesEvent) {
	event.registrar.also {
		it.registerGenericRoutes()
	}
}
