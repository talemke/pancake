package net.tassia.pancake.event

import kotlinx.coroutines.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.scheduler.Scheduler

class CallableEvent<E : Event> {

	private var listenersSync: Array<SyncListener<E>> = emptyArray()
	private var listenersAsync: Array<AsyncListener<E>> = emptyArray()



	fun callSync(scheduler: Scheduler, listeners: Array<SyncListener<E>>, event: E) {
		for (listener in listeners) {
			listener.listener(event)
		}
	}

	suspend fun callAsync(scheduler: Scheduler, plugin: Plugin, listeners: Array<AsyncListener<E>>, event: E) {
		// TODO: Use Scheduler
		listeners.map {
			GlobalScope.async {
				it.listener(event)
			}
		}.awaitAll()
	}



	fun addSyncListener(listener: SyncListener<E>) {
		listenersSync += listener
	}

	fun addAsyncListener(listener: AsyncListener<E>) {
		listenersAsync += listener
	}

	fun removeByPlugin(plugin: Plugin) {
		listenersSync = listenersSync.filter { it.plugin != plugin }.toTypedArray()
		listenersAsync = listenersAsync.filter { it.plugin != plugin }.toTypedArray()
	}



	fun callEvent(scheduler: Scheduler, plugin: Plugin, event: E): Job {
		val listenersSync = listenersSync
		val listenersAsync = listenersAsync

		// Immediately call sync listeners
		callSync(scheduler, listenersSync, event)

		// Create job for async listeners
		// TODO: Use Scheduler
		return GlobalScope.launch {
			callAsync(scheduler, plugin, listenersAsync, event)
		}
	}

	fun callEventAsync(scheduler: Scheduler, plugin: Plugin, event: E): Deferred<Unit> {
		val listenersSync = listenersSync
		val listenersAsync = listenersAsync

		// TODO: Use Scheduler
		return GlobalScope.async(start = CoroutineStart.LAZY) {
			callSync(scheduler, listenersSync, event)
			callAsync(scheduler, plugin, listenersAsync, event)
		}
	}

	suspend fun callEventSync(scheduler: Scheduler, plugin: Plugin, event: E) {
		val listenersSync = listenersSync
		val listenersAsync = listenersAsync

		callSync(scheduler, listenersSync, event)
		callAsync(scheduler, plugin, listenersAsync, event)
	}

}
