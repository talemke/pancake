package net.tassia.pancake.event

import net.tassia.pancake.Pancake
import net.tassia.pancake.api.event.Event
import net.tassia.pancake.plugin.Plugin

/**
 * All events used by Pancake extend this super event.
 * This allows all events (and listeners) to access a [Pancake] instance.
 *
 * @param pancake the Pancake instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
open class PluginEvent(

	val plugin: Plugin,

) : PancakeEvent(plugin.pancake)
