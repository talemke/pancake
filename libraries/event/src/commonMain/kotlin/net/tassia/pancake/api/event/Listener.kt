package net.tassia.pancake.api.event

/**
 * A listener listens for a specific event.
 *
 * @since PancakeEvent 1.0
 * @author Tassilo
 */
fun interface Listener<E : Event> {

	/**
	 * Invoked when the event is dispatched.
	 *
	 * @param event the event
	 */
	fun onEvent(event: E)

}
