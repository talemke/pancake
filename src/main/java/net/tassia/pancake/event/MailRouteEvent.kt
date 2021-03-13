package net.tassia.pancake.event

import net.tassia.event.Cancellable
import net.tassia.event.Event
import net.tassia.pancake.entity.account.Account
import net.tassia.pancake.entity.folder.Folder
import net.tassia.pancake.entity.mail.Mail
import net.tassia.pancake.entity.route.Route

/**
 * Called when a [Mail] is about to be routed.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class MailRouteEvent(

	/**
	 * The mail that should be routed.
	 */
	val mail: Mail,

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

) : Event(), Cancellable
