package net.tassia.pancake.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.api.event.Event

/**
 * All events used by Pancake extend this super event.
 * This allows all events (and listeners) to access a [Pancake] instance.
 *
 * @param pancake the Pancake instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class PancakeEvent(

	val pancake: Pancake,

) : Event()
