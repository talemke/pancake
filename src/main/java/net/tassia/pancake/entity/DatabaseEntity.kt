package net.tassia.pancake.entity

/**
 * Represents a database entity, that can be reloaded and deleted.
 *
 * @since Pancake 1.0
 */
interface DatabaseEntity<E : DatabaseEntity<E>> {

	/**
	 * Reloads this entity.
	 *
	 * @return the entity
	 */
	suspend fun reload(): E

	/**
	 * Deletes this entity.
	 */
	suspend fun delete()

}
