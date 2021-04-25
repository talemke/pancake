package net.tassia.pancake.plugin.base.event.mail

import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.Mail
import net.tassia.pancake.event.generic.MailEvent
import net.tassia.pancake.event.PancakeEvent

/**
 * This event is called when a new [Mail] has been received.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class IncomingMailEvent(

	override val mail: Mail,
	pancake: Pancake,

) : PancakeEvent(pancake), MailEvent
