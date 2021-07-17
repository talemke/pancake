package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration

/**
 * A list containing all migrations.
 */
val migrations: List<Migration> = listOf<Migration>().sortedBy(Migration::index).also {

	// Assert that indices are properly ordered
	it.forEachIndexed { index, migration ->
		require(migration.index == index)
	}

}
