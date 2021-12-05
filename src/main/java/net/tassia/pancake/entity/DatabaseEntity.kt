package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction

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
	fun reload(transaction: Transaction): E

	/**
	 * Deletes this entity.
	 */
	fun delete(transaction: Transaction)

}
