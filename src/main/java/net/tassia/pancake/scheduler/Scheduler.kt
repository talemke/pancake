package net.tassia.pancake.scheduler

import kotlinx.coroutines.Job
import net.tassia.pancake.plugin.Plugin

/**
 * Used to start asynchronous and repeating tasks.
 *
 * @since Jet 1.0
 */
abstract class Scheduler {

	abstract fun executeAsync(name: String, plugin: Plugin, block: suspend () -> Unit): Job

	// TODO: Scheduled/Repeating Tasks

}
