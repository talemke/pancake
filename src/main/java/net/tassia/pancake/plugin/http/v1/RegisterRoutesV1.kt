package net.tassia.pancake.plugins.http.v1

import net.tassia.event.EventListener
import net.tassia.pancake.plugins.http.event.HttpRegisterRoutesEvent

/**
 * Registers all routes of the Version 1 API. This function acts as an
 * event listener, thus it takes an [HttpRegisterRoutesEvent] as argument.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object RegisterRoutesV1 : EventListener<HttpRegisterRoutesEvent> {

	override fun onEvent(event: HttpRegisterRoutesEvent) {
		event.registrar.also {
			it.registerGenericRoutes()
		}
	}

}
