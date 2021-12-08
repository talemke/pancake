package net.tassia.pancake.scheduler

import kotlinx.coroutines.*
import net.tassia.pancake.plugin.Plugin

class StandardScheduler : Scheduler() {

	internal val eventLoop = runBlocking {
		launch(start = CoroutineStart.LAZY) { executeEventLoop() }
	}

	private suspend fun CoroutineScope.executeEventLoop() {
		// TODO

		// Wait
		awaitCancellation()
	}





	override fun executeAsync(name: String, plugin: Plugin, block: AsyncFunction): Task {
		TODO()
	}





	override fun createLocalRepeatingTask(name: String, plugin: Plugin, delay: Long, initialDelay: Long, block: AsyncFunction): Task {
		TODO("Not yet implemented")
	}

	override fun createLocalDelayedTask(name: String, plugin: Plugin, delay: Long, block: AsyncFunction): Task {
		TODO("Not yet implemented")
	}

}
