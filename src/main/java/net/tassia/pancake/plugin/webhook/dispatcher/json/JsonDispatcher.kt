package net.tassia.pancake.plugin.webhook.dispatcher.json

import net.tassia.pancake.plugin.webhook.dispatcher.AbstractJsonDispatcher
import net.tassia.pancake.plugin.webhook.entity.event.MailMovedEvent
import net.tassia.pancake.plugin.webhook.entity.event.MailReceivedEvent
import net.tassia.pancake.plugin.webhook.entity.event.MailSentEvent
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent
import java.lang.IllegalArgumentException

/**
 * The default JSON dispatcher.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object JsonDispatcher : AbstractJsonDispatcher() {

	override fun buildJson(event: WebhookEvent): Any {
		return when (event) {
			is MailReceivedEvent -> JsonMailReceivedEvent(event)
			is MailMovedEvent -> JsonMailMovedEvent(event)
			is MailSentEvent -> JsonMailSentEvent(event)
			else -> throw IllegalArgumentException("Unknown event: " + event::class.simpleName)
		}
	}

}
