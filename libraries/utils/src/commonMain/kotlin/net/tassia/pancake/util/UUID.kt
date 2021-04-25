package net.tassia.pancake.util

expect class UUID(mostSignificant: Long, leastSignificant: Long) : Comparable<UUID> {

	override fun toString(): String

}
