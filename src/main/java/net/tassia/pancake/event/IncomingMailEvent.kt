package net.tassia.pancake.event

import net.tassia.event.Event
import net.tassia.pancake.entity.mail.Mail

/**
 * This event is called when a new [Mail] has been received.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class IncomingMailEvent(

	/**
	 * The incoming mail.
	 */
	val mail: Mail

) : Event()
