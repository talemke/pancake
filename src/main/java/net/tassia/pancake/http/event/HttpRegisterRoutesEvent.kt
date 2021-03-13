package net.tassia.pancake.http.event

import net.tassia.event.Event
import net.tassia.pancake.http.RouteRegistrar

/**
 * Called when the HTTP server is about to startup so all systems/plugins can register their routes.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class HttpRegisterRoutesEvent(

	/**
	 * The route registrar.
	 */
	val registrar: RouteRegistrar

) : Event()
