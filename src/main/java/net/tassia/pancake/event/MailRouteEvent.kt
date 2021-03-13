package net.tassia.pancake.event

import net.tassia.event.Cancellable
import net.tassia.event.Event
import net.tassia.pancake.entity.account.Account
import net.tassia.pancake.entity.folder.Folder
import net.tassia.pancake.entity.mail.Mail

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
	 * The account to route this mail to.
	 */
	val account: Account?,

	/**
	 * The folder to route this mail to.
	 */
	val folder: Folder?

) : Event(), Cancellable {

	private var cancelled = false

	override fun isCancelled(): Boolean {
		return cancelled
	}

	override fun setCancelled(cancelled: Boolean) {
		this.cancelled = cancelled
	}

}
