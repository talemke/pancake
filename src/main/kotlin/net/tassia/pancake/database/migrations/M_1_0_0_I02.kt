package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration
import net.tassia.pancake.database.table.AccountTable
import net.tassia.pancake.database.table.GroupTable
import org.jetbrains.exposed.sql.SchemaUtils

@Suppress("ClassName")
internal object M_1_0_0_I02 : Migration(1, "1.0.0/02") {

	override fun execute() {
		SchemaUtils.create(GroupTable)
	}

}
