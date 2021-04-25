package net.tassia.pancake.api.event

import kotlin.reflect.KClass

class EventManager {

	private val register: MutableCollection<RListener<*>> = mutableListOf()

	fun <T : Event> register(listener: Listener<T>, klass: KClass<T>) {
		register.add(RListener(listener, klass))
	}

	inline fun <reified T : Event> register(listener: Listener<T>) {
		register(listener, T::class)
	}



	fun <E : Event> call(event: E, eventClass: KClass<E>) {
		for (r in register) {
			if (r.klass == eventClass) {
				(r as RListener<E>).listener.onEvent(event)
			}
		}
	}

	inline fun <reified E : Event> call(event: E) {
		call(event, E::class)
	}



	private class RListener<E : Event>(
		val listener: Listener<E>,
		val klass: KClass<E>,
	)

}
