package net.tassia.pancake.entity

interface DatabaseEntity<E : DatabaseEntity<E>> {

	suspend fun reload(): E
	suspend fun delete()

}
