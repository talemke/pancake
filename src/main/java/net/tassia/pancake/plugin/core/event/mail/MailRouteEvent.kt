package net.tassia.pancake.plugin.core.event.mail

import net.tassia.event.Cancellable
import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.account.Account
import net.tassia.pancake.entity.folder.Folder
import net.tassia.pancake.entity.mail.Mail
import net.tassia.pancake.entity.route.Route
import net.tassia.pancake.event.generic.MailEvent
import net.tassia.pancake.event.PancakeEvent

/**
 * Called when a [Mail] is about to be routed.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class MailRouteEvent(

	override val mail: Mail,
	override val pancake: Pancake,

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
