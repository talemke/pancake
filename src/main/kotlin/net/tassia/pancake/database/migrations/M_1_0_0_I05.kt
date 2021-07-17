package net.tassia.pancake.database.migrations

import net.tassia.pancake.database.migration.Migration
import net.tassia.pancake.database.table.*
import org.jetbrains.exposed.sql.SchemaUtils

@Suppress("ClassName")
internal object M_1_0_0_I05 : Migration(4, "1.0.0/05") {

	override fun execute() {
		SchemaUtils.create(RouteTable)
	}

}
