package net.tassia.pancake.scheduler

import kotlinx.coroutines.Job
import net.tassia.pancake.plugin.Plugin

class StandardScheduler : Scheduler() {

	override fun executeAsync(name: String, plugin: Plugin, block: suspend () -> Unit): Job {
		TODO("Not yet implemented")
	}

}
