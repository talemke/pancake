package net.tassia.pancake.plugin.webhook.event

import net.tassia.pancake.event.PancakeEvent
import net.tassia.pancake.plugin.webhook.WebhookPlugin

/**
 * All Webhook related events extend this class.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class WebhookPluginEvent(

	/**
	 * The current [WebhookPlugin].
	 */
	open val plugin: WebhookPlugin,

) : PancakeEvent(plugin.pancake)
