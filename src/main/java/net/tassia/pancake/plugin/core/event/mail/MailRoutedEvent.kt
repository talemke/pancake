package net.tassia.pancake.plugin.core.event.mail

import net.tassia.pancake.Pancake
import net.tassia.pancake.entity.account.Account
import net.tassia.pancake.entity.folder.Folder
import net.tassia.pancake.entity.mail.Mail
import net.tassia.pancake.entity.route.Route
import net.tassia.pancake.event.MailEvent

/**
 * Called when a [Mail] has been received and routed.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class MailRoutedEvent(

	override val mail: Mail,
	override val pancake: Pancake,

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

) : MailEvent(mail, pancake)
