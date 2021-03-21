package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.mail.Mail

/**
 * Declares that a given event involves a [Mail].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface MailEvent {

	/**
	 * The mail.
	 */
	val mail: Mail

}
