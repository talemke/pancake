package net.tassia.pancake.plugin.webhook.event

import net.tassia.pancake.plugin.webhook.WebhookPlugin
import net.tassia.pancake.plugin.webhook.entity.Webhook
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent

/**
 * Called when a webhook has been dispatched.
 *
 * @param webhook the webhook
 * @param event the webhook event
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class WebhookDispatchedEvent(

	override val plugin: WebhookPlugin,

	/**
	 * The dispatched webhook.
	 */
	val webhook: Webhook,

	/**
	 * The event for the webhook.
	 */
	val event: WebhookEvent,

) : WebhookPluginEvent(plugin)
