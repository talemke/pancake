package net.tassia.pancake.event

import net.tassia.pancake.Pancake

/**
 * Called after Pancake has successfully initialized.
 * This should only be used for monitoring/logging, the heavy
 * work should be done in the `onLoad` and `onEnable` functions.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PancakePostInitEvent(

	pancake: Pancake,

) : PancakeEvent(pancake)
