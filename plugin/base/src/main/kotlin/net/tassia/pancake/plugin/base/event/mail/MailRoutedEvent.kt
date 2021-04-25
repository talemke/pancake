package net.tassia.pancake.plugin.base.event.mail

import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.Account
import net.tassia.pancake.entity.Folder
import net.tassia.pancake.entity.Mail
import net.tassia.pancake.entity.Route
import net.tassia.pancake.event.generic.MailEvent
import net.tassia.pancake.event.PancakeEvent

/**
 * Called when a [Mail] has been received and routed.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MailRoutedEvent(

	override val mail: Mail,
	pancake: Pancake,

	/**
	 * The receiver address used for routing.
	 */
	val address: String,

	/**
	 * The account to which the mail has been routed to.
	 * Will be `null` if no receiver account has been found.
	 */
	val account: Account?,

	/**
	 * The folder to which this mail has been routed.
	 * Will be `null` if no target folder could be determined.
	 */
	val folder: Folder?,

	/**
	 * The route that has been used to route this mail.
	 */
	val route: Route?

) : PancakeEvent(pancake), MailEvent
