package net.tassia.pancake.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.mail.Mail

/**
 * Superclass for all mail related events.
 *
 * @param mail the mail
 * @param pancake the Pancake instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class MailEvent(

	/**
	 * The mail.
	 */
	open val mail: Mail,

	override val pancake: Pancake,

) : PancakeEvent(pancake)
