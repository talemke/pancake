package net.tassia.pancake.entity.webhook.event

/**
 * A webhook event.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
abstract class WebhookEvent(val id: Long, val name: String) {

	/**
	 * Generates the JSON payload for this webhook event.
	 *
	 * @return the JSON object
	 */
	abstract fun generateJSON(): Any

}
