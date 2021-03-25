package net.tassia.pancake.entity.webhook

/**
 * Utility object containing constants of webhook events.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object WebhookEventID {

	/**
	 * A new mail has been received.
	 */
	const val MAIL_RECEIVED = 1L

	/**
	 * A mail has been moved from one folder to another.
	 */
	const val MAIL_MOVED = 2L

	/**
	 * A mail has been sent.
	 */
	const val MAIL_SENT = 4L

}
