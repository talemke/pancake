package net.tassia.pancake.plugin.core.event.mail

import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.mail.Mail
import net.tassia.pancake.event.MailEvent
import net.tassia.pancake.event.PancakeEvent

/**
 * This event is called when a new [Mail] has been received.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class IncomingMailEvent(

	override val mail: Mail,
	override val pancake: Pancake,

) : PancakeEvent(pancake), MailEvent
