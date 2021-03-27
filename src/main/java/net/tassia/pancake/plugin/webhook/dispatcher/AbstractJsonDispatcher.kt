package net.tassia.pancake.plugin.webhook.dispatcher

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent

/**
 * An AbstractJsonDispatcher takes generated objects for events, converts them into JSON and then
 * sends them via the underlying [HttpDispatcher].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
abstract class AbstractJsonDispatcher : HttpDispatcher(ContentType.Application.Json) {

	/**
	 * The mapper for generating the JSON strings.
	 */
	private val mapper: ObjectMapper = ObjectMapper()

	/**
	 * Build a JSON object for the given webhook event.
	 *
	 * @param event the event
	 * @return the JSON object
	 */
	abstract fun buildJson(event: WebhookEvent): Any

	override fun buildBody(event: WebhookEvent): String {
		return mapper.writeValueAsString(buildJson(event))
	}

}
