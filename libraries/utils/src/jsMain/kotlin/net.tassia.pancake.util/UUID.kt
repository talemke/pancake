package net.tassia.pancake.util

actual class UUID actual constructor(mostSignificant: Long, leastSignificant: Long) : Comparable<UUID> {

	actual override fun toString(): String {
		return super.toString()
	}

	override fun compareTo(other: UUID): Int {
		TODO("Not yet implemented")
	}

}
