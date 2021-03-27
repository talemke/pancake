package net.tassia.pancake.plugin.webhook.dispatcher

import kotlinx.coroutines.Deferred
import net.tassia.pancake.plugin.webhook.entity.Webhook
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent

/**
 * A dispatcher is responsible for dispatching/delivering a webhook.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface WebhookDispatcher {

	/**
	 * Invoked to dispatch a webhook for an event.
	 *
	 * @param webhook the webhook to dispatch
	 * @param event the event
	 * @return the response
	 */
	fun dispatchAsync(webhook: Webhook, event: WebhookEvent): Deferred<String>

}
