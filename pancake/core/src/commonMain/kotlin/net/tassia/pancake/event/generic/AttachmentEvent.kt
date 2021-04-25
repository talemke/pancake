package net.tassia.pancake.event.generic

import net.tassia.pancake.entity.Attachment

/**
 * Declares that a given event involves an [Attachment].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
interface AttachmentEvent {

	/**
	 * The attachment.
	 */
	val attachment: Attachment

}
