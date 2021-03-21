package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.session.Session

/**
 * Declares that a given event involves a [Session].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface SessionEvent {

	/**
	 * The session.
	 */
	val session: Session

}
