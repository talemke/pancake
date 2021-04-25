package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.Group

/**
 * Declares that a given event involves a [Group].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface GroupEvent {

	/**
	 * The group.
	 */
	val group: Group

}
