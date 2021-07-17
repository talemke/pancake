package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration
import net.tassia.pancake.database.table.AccountTable
import org.jetbrains.exposed.sql.SchemaUtils

@Suppress("ClassName")
internal object M_1_0_0_I01 : Migration(0, "1.0.0/01") {

	override fun execute() {
		SchemaUtils.create(AccountTable)
	}

}
