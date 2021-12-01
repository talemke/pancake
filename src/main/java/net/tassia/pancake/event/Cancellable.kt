package net.tassia.pancake.event

/**
 * Declares an event to be cancellable.
 *
 * @since Jet 1.0
 */
interface Cancellable {

	/**
	 * Whether the event is currently cancelled.
	 */
	var isCancelled: Boolean

}
