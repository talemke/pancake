package net.tassia.pancake.database.migration

/**
 * Defines a simple database migration. Migrations are used to prepare the database for usage
 * by this application (i.e. table creation & population).
 *
 * @param index the index of this migration (all migrations must be in sequential order, starting at `0`)
 * @param name a display name for this migration
 *
 * @since Pancake 1.0
 * @authorTassilo
 */
abstract class Migration(val index: Int, val name: String) {

	/**
	 * Executes this migration.
	 *
	 * **Note:** It can safely be assumed that an active transaction is present when this function is invoked.
	 */
	abstract fun execute()

}
