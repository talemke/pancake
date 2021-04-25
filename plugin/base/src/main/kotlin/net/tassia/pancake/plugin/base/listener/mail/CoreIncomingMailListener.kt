package net.tassia.pancake.plugin.base.listener.mail

import net.tassia.pancake.api.event.Listener
import net.tassia.pancake.plugin.base.BasePlugin
import net.tassia.pancake.plugin.base.event.mail.IncomingMailEvent
import net.tassia.pancake.plugin.base.event.mail.MailRouteEvent
import net.tassia.pancake.plugin.base.event.mail.MailRoutedEvent

/**
 * Listens for incoming mails and does magic do make sure it is treated right.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class CoreIncomingMailListener(private val core: BasePlugin) : Listener<IncomingMailEvent> {

	override fun onEvent(event: IncomingMailEvent) {
		for (address in event.mail.recipients.split(",")) {
			initializeRouting(event, address)
		}
	}

	private fun initializeRouting(event: IncomingMailEvent, address: String) {
		val pan = event.pancake

		// Tell everyone that we want to route this mail
		core.info("Mail ${event.mail} received. Routing it for $address")
		val routeEvent = MailRouteEvent(event.mail, pan, address, null, null, null)
		pan.events.call(routeEvent)

		// Tell everyone that we routed this mail
		core.info("Mail ${event.mail} received and routed for $address")
		pan.events.call(MailRoutedEvent(event.mail, pan, address, routeEvent.account, routeEvent.folder, routeEvent.route))

		// Spam protection
		// TODO

		// Execute webhooks
		// TODO
	}

}
