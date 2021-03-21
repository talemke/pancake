package net.tassia.pancake.plugin.http.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.event.PancakeEvent
import net.tassia.pancake.plugin.http.RouteRegistrar

/**
 * Called when the HTTP server is about to startup so all systems/plugins can register their routes.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class HttpRegisterRoutesEvent(

	override val pancake: Pancake,

	/**
	 * The route registrar.
	 */
	val registrar: RouteRegistrar

) : PancakeEvent(pancake)
