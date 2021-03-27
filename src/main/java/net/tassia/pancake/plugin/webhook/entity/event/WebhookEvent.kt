package net.tassia.pancake.plugin.webhook.entity.event

import net.tassia.pancake.entity.folder.Folder
import net.tassia.pancake.entity.mail.Mail

/**
 * A webhook event.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class WebhookEvent(val id: Long, val name: String)



/**
 * Dispatched when a new mail has been received.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MailReceivedEvent(

	val mail: Mail,

) : WebhookEvent(WebhookEventID.MAIL_RECEIVED, "mail_received")



/**
 * Dispatched when a mail has been moved.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MailMovedEvent(

	val mail: Mail,
	val from: Folder,
	val to: Folder,

) : WebhookEvent(WebhookEventID.MAIL_MOVED, "mail_moved")



/**
 * Dispatched when a mail has been sent.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MailSentEvent(

	val mail: Mail,

) : WebhookEvent(WebhookEventID.MAIL_SENT, "mail_sent")
