package net.tassia.pancake.api.event

/**
 * Declares that a given [Event] can be cancelled.
 *
 * @since PancakeEvent 1.0
 * @author Tassilo
 */
interface Cancellable {

	/**
	 * Whether this event is currently cancelled.
	 */
	var isCancelled: Boolean

}
