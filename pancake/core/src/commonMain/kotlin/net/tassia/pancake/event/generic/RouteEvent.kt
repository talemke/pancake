package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.Route

/**
 * Declares that a given event involves a [Route].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface RouteEvent {

	/**
	 * The route.
	 */
	val route: Route

}
