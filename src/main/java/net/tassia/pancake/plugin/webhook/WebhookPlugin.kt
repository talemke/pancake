package net.tassia.pancake.plugin.webhook

import net.tassia.event.EventManager
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugin.webhook.dispatcher.WebhookDispatcher
import net.tassia.pancake.plugin.webhook.dispatcher.json.JsonDispatcher
import net.tassia.pancake.plugin.webhook.entity.Webhook
import net.tassia.pancake.plugin.webhook.entity.WebhookPayloadType
import net.tassia.pancake.plugin.webhook.entity.WebhookTable
import net.tassia.pancake.plugin.webhook.entity.event.WebhookEvent
import net.tassia.pancake.plugin.webhook.event.WebhookDispatchEvent
import net.tassia.pancake.plugin.webhook.event.WebhookDispatchedEvent
import net.tassia.pancake.plugin.webhook.event.WebhookPluginEvent
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * The webhook plugin for Pancake. Adds webhooks.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class WebhookPlugin(override val pancake: Pancake) : Plugin(pancake) {

	override val info: PluginInfo = Info



	private fun determineDispatcher(type: WebhookPayloadType): WebhookDispatcher {
		return when (type) {
			WebhookPayloadType.JSON -> JsonDispatcher
			else -> throw IllegalArgumentException("Unknown WebhookPayloadType: $type")
		}
	}

	fun dispatch(webhook: Webhook, event: WebhookEvent) {
		// Call event
		WebhookDispatchEvent(this, webhook, event).run {
			pancake.events.callEvent(this)
			if (this.isCancelled) return
		}

		// Dispatch webhook
		val result = determineDispatcher(webhook.contentType).dispatchAsync(webhook, event)

		// Call event
		result.invokeOnCompletion {
			if (it != null) {
				warn("Failed to dispatch webhook.", it)
			}
			pancake.events.callEvent(WebhookDispatchedEvent(this, webhook, event))
		}
	}



	override fun onEnable() {
		// Register listeners
		// TODO
	}

	override fun onInstall() {
		transaction {
			SchemaUtils.create(WebhookTable)
		}
	}

	override fun onUninstall() {
		transaction {
			SchemaUtils.drop(WebhookTable)
		}
	}



	companion object {

		/**
		 * The version information for the core plugin.
		 */
		private val Version = Pancake.VERSION

		/**
		 * Registers all events for this plugin.
		 */
		private val RegisterEvents = { events: EventManager ->
			events.registerEvent<WebhookPluginEvent>()
			events.registerEvent<WebhookDispatchEvent>()
			events.registerEvent<WebhookDispatchedEvent>()
		}

		/**
		 * The plugin information for the core plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:Webhook",
			name = "Webhook",
			description = "Adds webhooks to Pancake.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::WebhookPlugin,
			events = RegisterEvents,
		)

	}

}
