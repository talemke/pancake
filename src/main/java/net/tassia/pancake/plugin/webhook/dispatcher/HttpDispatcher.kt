package net.tassia.pancake.plugin.webhook.dispatcher

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.webhook.entity.Webhook
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent

/**
 * An HTTP dispatcher dispatches webhooks via HTTP.
 *
 * @param contentType the content-type to send
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
abstract class HttpDispatcher(private val contentType: ContentType) : WebhookDispatcher {

	/**
	 * The underlying HTTP client used to send the requests.
	 */
	private val client = HttpClient(Apache)

	/**
	 * Generate the request body for the webhook.
	 *
	 * @param event the event
	 * @return the request body
	 */
	abstract fun buildBody(event: WebhookEvent): String

	override fun dispatchAsync(webhook: Webhook, event: WebhookEvent): Deferred<String> {
		val payload = buildBody(event)
		return GlobalScope.async {
			return@async client.post<String>(webhook.url) {
				// Generic headers
				header("User-Agent", "Pancake/" + Pancake.VERSION)

				// Pancake headers
				header("Pancake-Event", event.name)
				header("Pancake-Version", Pancake.VERSION)

				// Send payload
				body = TextContent(payload, contentType)
			}
		}
	}

}
