package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration

/**
 * A list containing all migrations.
 */
val migrations: List<Migration> = listOf(

	M_1_0_0_I01,
	M_1_0_0_I02,
	M_1_0_0_I03,
	M_1_0_0_I04,
	M_1_0_0_I05,

).sortedBy(Migration::index).also {

	// Assert that indices are properly ordered
	it.forEachIndexed { index, migration ->
		require(migration.index == index)
	}

}
