package net.tassia.pancake.plugin.base.event.mail

import net.tassia.pancake.Pancake
import net.tassia.pancake.api.event.Cancellable
import net.tassia.pancake.entity.Account
import net.tassia.pancake.entity.Folder
import net.tassia.pancake.entity.Mail
import net.tassia.pancake.entity.Route
import net.tassia.pancake.event.generic.MailEvent
import net.tassia.pancake.event.PancakeEvent

/**
 * Called when a [Mail] is about to be routed.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MailRouteEvent(

	override val mail: Mail,
	pancake: Pancake,

	/**
	 * The receiver address to use for routing.
	 */
	val address: String,

	/**
	 * The account to route this mail to.
	 */
	var account: Account?,

	/**
	 * The folder to route this mail to.
	 */
	var folder: Folder?,

	/**
	 * The route that has been used to route this mail.
	 */
	var route: Route?,

	override var isCancelled: Boolean = false

) : PancakeEvent(pancake), MailEvent, Cancellable
