package net.tassia.pancake.scheduler

import net.tassia.pancake.plugin.Plugin

/**
 * Used to start asynchronous and repeating tasks.
 *
 * @since Pancake 1.0
 */
abstract class Scheduler {

	/**
	 * Creates & executes a new asynchronous task on the local node.
	 *
	 * @param name the name of the job
	 * @param plugin the initiating plugin
	 * @param block the block to execute
	 * @return the created task
	 */
	abstract fun executeAsync(name: String, plugin: Plugin, block: AsyncFunction): Task





	abstract fun createLocalDelayedTask(name: String, plugin: Plugin, delay: Long, block: AsyncFunction): Task
	abstract fun createLocalRepeatingTask(name: String, plugin: Plugin, delay: Long, initialDelay: Long, block: AsyncFunction): Task

	// TODO: Scheduled/Repeating Tasks

}
