package net.tassia.pancake.plugin.core.listener.mail

import net.tassia.event.EventListener
import net.tassia.pancake.plugin.core.event.mail.MailRoutedEvent

/**
 * Listens for routed mails, and either moves them to their folder, or, if they have not been routed, discards them.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object CoreMailRoutedListener : EventListener<MailRoutedEvent> {

	override fun onEvent(event: MailRoutedEvent) {
		// Get the folder
		val folder = event.folder ?: event.route?.target

		// Successfully routed? Yayy.
		if (folder != null) {
			event.mail.folder = folder.id
			return
		}

		// Not successfully routed. Oh no.
		TODO()
	}

}
