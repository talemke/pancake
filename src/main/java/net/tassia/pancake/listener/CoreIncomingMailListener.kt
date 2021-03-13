package net.tassia.pancake.listener

import net.tassia.event.EventListener
import net.tassia.pancake.event.IncomingMailEvent
import net.tassia.pancake.event.MailRouteEvent
import net.tassia.pancake.event.MailRoutedEvent

/**
 * Listens for incoming mails and does magic do make sure it is treated right.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object CoreIncomingMailListener : EventListener<IncomingMailEvent> {

	override fun onEvent(event: IncomingMailEvent) {
		for (address in event.mail.recipients.split(",")) {
			initializeRouting(event, address)
		}
	}

	private fun initializeRouting(event: IncomingMailEvent, address: String) {
		val pan = event.pancake

		// Tell everyone that we want to route this mail
		pan.logger.info("Mail ${event.mail} received. Routing it for $address")
		val routeEvent = MailRouteEvent(pan, event.mail, address, null, null, null)
		pan.events.callEvent(routeEvent)

		// Tell everyone that we routed this mail
		pan.logger.info("Mail ${event.mail} received and routed for $address")
		pan.events.callEvent(MailRoutedEvent(pan, event.mail, address, routeEvent.account, routeEvent.folder, routeEvent.route))
	}

}
