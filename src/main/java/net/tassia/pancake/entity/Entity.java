package net.tassia.pancake.entity;

/**
 * The base interface for all database entities.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public interface Entity {

	/**
	 * Reloads this entity. This discards all currently pending changes to this entity
	 * and resets it to the state as stored in the database.
	 */
	void reload();

	/**
	 * Updates this entity. This means that all currently pending changes will be committed
	 * to the database and this instance is synchronized with the database again.
	 */
	void update();

	/**
	 * Deletes this entity. This makes sure this entity is erased from the database.
	 * This method does nothing, if the entity has already been deleted.
	 *
	 * <b>Note:</b> This function does <b>not</b> delete references to this entity.
	 */
	void delete();

}
