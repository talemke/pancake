package net.tassia.pancake.event

import kotlin.reflect.KClass

class EventManager {

	private var events: Map<KClass<*>, CallableEvent<*>> = mapOf()



	fun <E : Event> event(klass: KClass<E>): CallableEvent<E> {
		// Exists?
		events[klass]?.also {
			@Suppress("UNCHECKED_CAST")
			return it as CallableEvent<E>
		}

		// Create
		return CallableEvent<E>().also {
			events = events + (klass to it)
		}
	}

	inline fun <reified E : Event> event(): CallableEvent<E> {
		return event(E::class)
	}

}
