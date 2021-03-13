package net.tassia.pancake

import net.tassia.event.EventManager
import java.util.logging.Logger

/**
 * The base class for Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Pancake {

	/**
	 * Used to handle events for Pancake.
	 */
	val events = EventManager.newDefault()

	/**
	 * The logger for Pancake.
	 */
	val logger = Logger.getLogger("Pancake")



	init {
		// TODO: Actually initialize Pancake
	}



	companion object {

		/**
		 * The hard-coded limit of how long a single mail address ('name@hostname.tld') can be (in bytes).
		 */
		const val MAIL_ADDRESS_MAX = 255

		/**
		 * The hard-coded limit of how long a mail subject can be (in bytes).
		 */
		const val MAIL_SUBJECT_MAX = 255

		/**
		 * The hard-coded limit of how long a mails displayable content can be (in bytes).
		 */
		const val MAIL_CONTENT_MAX = Int.MAX_VALUE

		/**
		 * The hard-coded limit of how many recipients a single mail can have at maximum.
		 */
		const val MAIL_RECIPIENTS_MAX = 63

	}

}
