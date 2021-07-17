package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration
import net.tassia.pancake.database.table.AccountTable
import net.tassia.pancake.database.table.GroupTable
import net.tassia.pancake.database.table.InboxTable
import org.jetbrains.exposed.sql.SchemaUtils

@Suppress("ClassName")
internal object M_1_0_0_I03 : Migration(2, "1.0.0/03") {

	override fun execute() {
		SchemaUtils.create(InboxTable)
	}

}
