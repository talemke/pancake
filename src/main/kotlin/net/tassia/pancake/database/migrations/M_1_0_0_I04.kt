package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration
import net.tassia.pancake.database.table.AccountTable
import net.tassia.pancake.database.table.GroupTable
import net.tassia.pancake.database.table.InboxTable
import net.tassia.pancake.database.table.PrivilegeTable
import org.jetbrains.exposed.sql.SchemaUtils

@Suppress("ClassName")
internal object M_1_0_0_I04 : Migration(3, "1.0.0/04") {

	override fun execute() {
		SchemaUtils.create(PrivilegeTable)
	}

}
