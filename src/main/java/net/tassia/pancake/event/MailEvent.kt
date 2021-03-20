package net.tassia.pancake.event

import net.tassia.pancake.entity.mail.Mail

/**
 * All mail related events should implement this.
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
