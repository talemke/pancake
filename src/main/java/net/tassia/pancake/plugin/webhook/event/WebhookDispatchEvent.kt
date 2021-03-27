package net.tassia.pancake.plugin.webhook.event

import net.tassia.event.Cancellable
import net.tassia.pancake.plugin.webhook.WebhookPlugin
import net.tassia.pancake.plugin.webhook.entity.Webhook
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent

/**
 * Called when a webhook is about to be dispatched.
 *
 * @param webhook the webhook
 * @param event the webhook event
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class WebhookDispatchEvent(

	override val plugin: WebhookPlugin,

	/**
	 * The webhook that is about to be dispatched.
	 */
	val webhook: Webhook,

	/**
	 * The event for the webhook.
	 */
	val event: WebhookEvent,

	override var isCancelled: Boolean = false,

) : WebhookPluginEvent(plugin), Cancellable
